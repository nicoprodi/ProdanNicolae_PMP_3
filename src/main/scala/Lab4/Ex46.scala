import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.^^

object Ex46 {
    def main(args: Array[String]) {
        // To keep the code simple, I just make the cards an integer
        val cards = List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        // The discrete uniform distribution chooses uniformly from a fixed
        // set of possibilities
        val player1Card = discrete.Uniform(cards:_*)
        val player2Card = Chain(player1Card, (card: Int) =>
            // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card):_*)
        )
		
		val player1Card2 = Chain(player1Card, player2Card, (card: Int, card2: Int) =>
            // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card && _ !=card2):_*)
		)
		
		val playercards=^^(player1Card, player2Card, player1Card2)
		val player2Card2 = Chain(playercards , (cards:(Int, Int, Int)) =>
            // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != cards._1 && _ != cards._2 && _ != cards._3):_*)
        )
		
        val player1Bet1 = RichCPD(player1Card, player1Card2
            // Player 1 is more likely to bet with a higher card,
            // but will sometimes bet with a lower card to bluff
            (OneOf(10, 9, 8, 7),OneOf(10, 9, 8, 7) -> Flip(0.9),
            (*,*) -> Flip(0.4) // ×××Change this for part (c)×××
        )
		)
        
        val player2Bet = RichCPD(player2Card, player2Card2, player1Bet1,
            (OneOf(10, 9, 8, 7),OneOf(10, 9, 8, 7), *) -> Flip(0.9),
            (OneOf(10, 9, 8, 7), *, OneOf(false)) -> Flip(0.5),
            (OneOf(1,2,3),OneOf(1,2,3), OneOf(true) -> Flip(0.0),
			(*,*,*) -> Flip(0.3),
        )
		)
        
        val player1Bet2 =
        Apply(player1Card, player1Card2, player1Bet1, player2Bet,
            (card: Int, card2: Int, bet11: Boolean, bet2: Boolean) =>
            // Player 1’s second bet is only relevant if she passed the
            // first time and player 2 bet
            !bet11 && bet2 && (card == 10 || card == 9 || card == 8) && (card2 == 10 || card2 == 9 || card2 == 8)
        
        )
        // This element represents the gain to player 1 from the game. I have
        // made it an Element[Double] so I can query its mean.
        val player1Gain = Apply(player1Card, player2Card, player1Card2, player2Card2, player1Bet1, player2Bet, player1Bet2,
            (card1: Int, card2: Int, card11: Int, card22: Int, bet11: Boolean, bet2: Boolean, bet12: Boolean) =>
                if (!bet11 && !bet2) 0.0
                else if (bet11 && !bet2) 1.0
                else if (!bet11 && bet2 && !bet12) -1.0
                else if (card1+card11 > card2 + card22) 2.0
                else -2.0
        )
		
        player1Card.observe(4)
        player1Bet1.observe(true)
        val alg1 = VariableElimination(player1Gain)
        alg1.start()
        alg1.stop()
        println("Expected gain for betting:" + alg1.mean(player1Gain))

        player1Bet1.observe(false)
        val alg2 = VariableElimination(player1Gain)
        alg2.start()
        alg2.stop()
        println("Expected gain for passing:" + alg2.mean(player1Gain))
        player1Card.unobserve()
        player1Bet1.unobserve()

        player2Card.observe(3)
        player1Bet1.observe(true)
        player2Bet.observe(true)
        val alg3 = VariableElimination(player1Gain)
        alg3.start()
        alg3.stop()
        println("Expected gain for betting:" + alg3.mean(player1Gain))

        player2Bet.observe(false)
        val alg4 = VariableElimination(player1Gain)
        alg4.start()
        alg4.stop()
        println("Expected gain for passing:" + alg4.mean(player1Gain))

        player2Card.unobserve()
        player1Bet1.unobserve()
        player2Bet.unobserve()
    }
}
