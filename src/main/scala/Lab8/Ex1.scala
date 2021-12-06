package Lab8
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.If

object Ex3{
	def main(args: Array[String]) {
		val test = Constant("Test")


		
		val length = 10
		val tPass: Array[Element[Boolean]] = Array.fill(length)(Constant(false)) 
		tPass(0) = Flip(0.6) 
		
		for { t <- 1 until length } {
		tPass(t) = If(tPass(t - 1), Flip(0.6), Flip(0.3)) }
		
		println(VariableElimination.probability(tPass(0), true))
		println(VariableElimination.probability(tPass(1), true))
		println(VariableElimination.probability(tPass(2), true))
		println(VariableElimination.probability(tPass(3), true))
		println(VariableElimination.probability(tPass(4), true))
		println(VariableElimination.probability(tPass(5), true))
		println(VariableElimination.probability(tPass(6), true))
		println(VariableElimination.probability(tPass(7), true))
		println(VariableElimination.probability(tPass(8), true))
		println(VariableElimination.probability(tPass(9), true))
		
	}
}
