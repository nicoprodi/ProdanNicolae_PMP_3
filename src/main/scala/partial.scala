import com.cra.figaro.language._
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored.VariableElimination
object Partial {
	
	def runda(p : Boolean): Int = 
	{
		val die1 = FromRange(1, 7)	//zarul 1 ia o valoare de la 1 la 6
		val die2 = FromRange(1, 7)	//zarul 2 ia o valoare de la 1 la 6
		
		val total = Apply(die1, die2, (i1: Int, i2: Int) => i1 + i2)		//totalul la aruncarea zarurilor
		
		
		if(p)
		{
			if(total==11 || total==7)
				return 1
			else
				if(total==2 || total==3 || total==12)
					return 2
				else
					return 1
		}else
		{
			if(total==11 || total==7)
				return 1
			else
				if(total==2 || total==3 || total==12)
					return 1
				else
					return 0	
		}
						
	}
	
	def play(p1Wins: Int, p2Wins: Int, no: Int) : Int =
	{
		val ex=runda(true)
		val ex2= runda(false)
		if(ex==1)
			p1Wins+1;
		if(ex==2)
			p2Wins+1;
		if(ex2==1)
			p1Wins+1;
		if(ex2==2)
			p2Wins+1;
		
		p1Wins
	}
	
	
	def main(args: Array[String]) {
		val die1 = FromRange(1, 7)	//zarul 1 ia o valoare de la 1 la 6
		val die2 = FromRange(1, 7)	//zarul 2 ia o valoare de la 1 la 6
		
		val total = Apply(die1, die2, (i1: Int, i2: Int) => i1 + i2)		//totalul la aruncarea zarurilor
		println("Probabilitate p1 castiga")
		println(VariableElimination.probability(total, 11)+VariableElimination.probability(total, 7))	//suma probabilitatilor dintre total=11 sau total=7
		println("Probabilitate p2 castiga")
		println(VariableElimination.probability(total, 2)+VariableElimination.probability(total, 3)+VariableElimination.probability(total, 12))	//suma probabilitatilor dintre total=12 sau total=2 sau total=3
		//probabilitatea ca p1 sa castige este mai mare decat p2 sa castige pentru ca 7 are o sansa se poate obtine din mai multe variante(3,4),(2,5),(1,6)
		val p1Wins=0
		val p2Wins=0
		play(p1Wins, p2Wins, 0)
		println(VariableElimination.probability(p1Wins, 1))
		
	}
}
