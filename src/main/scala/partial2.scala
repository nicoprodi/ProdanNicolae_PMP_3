import com.cra.figaro.language.{Select, Apply, Constant, Element, Chain, Flip, Universe}
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf, *, ^^}
import com.cra.figaro.algorithm.factored.{VariableElimination, MPEVariableElimination}
import com.cra.figaro.algorithm.sampling.{Importance, MetropolisHastings, ProposalScheme, MetropolisHastingsAnnealer, Schedule}
import com.cra.figaro.algorithm.factored.beliefpropagation.{BeliefPropagation, MPEBeliefPropagation}
import com.cra.figaro.algorithm.factored.beliefpropagation.MPEBeliefPropagation
import com.cra.figaro.algorithm.OneTimeMPE

object partial2
{
	abstract class Autor
	{
		val pupular: Element[Boolean]
	}
	
	class Album(atr: Autor, val calitate: Float) extends Autor
	{
		pupular = Flip(0.2)
	}
	
	class Nominalizare(albm: Album) extends Album
	{
		
		def getProb() : Float
		{
			val ret = RichCPD(albm.pupular, albm.calitate,
			(true, 0.27) -> 0.003,
			(false, 0.27) -> 0.014,
			(true, 0.6) -> 0.043,
			(false, 0.6) -> 0.016,
			(true, 0.13) -> 0.18,
			(false, 0.13) -> 0.047
			)
			return ret
		}
		
	}
	
	def main(args: Array[String])
	{
		val b = Array[Element[new Autor()]]
		val a = Array[Element[new Album(true, 0.27)]]
		
	}
}
