import com.cra.figaro.language._
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex31 {
	val sunnyToday = Flip(0.2)
	val goodSideOfBed = Flip(0.5)
	val greetingToday = If(sunnyToday,
		Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
		If(goodSideOfBed,
		Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again!"),
		Constant("Oh no, not again!")
		)
	)


def main(args: Array[String]) {
    val result = VariableElimination.probability(greetingToday, "Oh no, not again!")
    println("Probability that today’s greeting is \"Oh no, not again!\": " + result)

    greetingToday.observe("Oh no, not again!")
    println(VariableElimination.probability(sunnyToday, true))
  }
}