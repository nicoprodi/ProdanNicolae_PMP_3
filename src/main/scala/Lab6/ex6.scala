import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Apply, Constant, Element, Flip}
import com.cra.figaro.library.compound.If
import Array._
import scala.collection._
import com.cra.figaro.language.{Element, Chain, Apply}
import com.cra.figaro.language.Select
import com.cra.figaro.library.atomic.continuous.Uniform
import com.cra.figaro.library.collection.Container
import com.cra.figaro.algorithm.factored.VariableElimination


object Ex63
{
	def golf(s1: Double, par1: Int): Element[Int] =
	{
		def round(s: Double, par: Int): Element[Int] =
		{
			val shot =Select(
					s/8.0 				  		  -> (par-2),
					s/2.0  						  -> (par-1),
					s 							  ->  par,
					(4.0/5.0) * (1 -(s * 13)/8.0) -> (par+1),
					(1.0/5.0) * (1 -(s * 13)/8.0) -> (par+2)
			)
			shot
		}
	
		def play(s: Double, par: Int): Element[Int] =
		{
			val scor2=0
			val r = new scala.util.Random
			for( i <-1 to 18)
			{	
				val r1 = 2 + r.nextInt(par+1)
				
				val scor= round(s, r1)
				println(scor)
			}
	
		}	
		play(s1, par1)
	}
	



	def main(args: Array[String])
	{
		val scor=golf( 0.2, 5)
	
	
	
	}
}
