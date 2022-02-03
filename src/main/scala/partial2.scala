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
	
	class Initial() extends Autor
	{
		val pupular = Flip(0.2)
	}

	
	abstract class Album(atr: Autor) extends Autor
	{
		calitate: Float
	}
	
	class Nominalizare(albm: Album) extends Album
	{
		val x1 : Element[Boolean]
		val x2 : Float
		def getProb()
		{
			if(!albm.atr.pupular && calitate== 0.13)
			{
				x1 = true
			}
		}
		
	}
	
	def main(args: Array[String])
	{


	}
}
