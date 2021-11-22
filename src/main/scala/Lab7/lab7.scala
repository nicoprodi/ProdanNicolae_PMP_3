import com.cra.figaro.language.{Element, Select, Flip, Apply, Chain}
import com.cra.figaro.library.compound.{^^}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.sampling.Importance

object Departments {

	class ResearchAndDevelopment {
		val state = Uniform(FromRange(1,101))
	}

	class HumanResources {
		val state = Uniform(FromRange(1,101))
	}

	class Production(val rd: ResearchAndDevelopment, val hr: HumanResources) {
		val state = Apply(rd.state, hr.state, (s1 : Int, s2 : Int) => (s1+s2)/2)
	}

	class Sales(val p: Production) {
		val state = Apply(p.state, (s : Int) => s-s/10)
	}

	class Finance(val hr: HumanResources, val s: Sales) {
		val state = Apply(hr.state, s.state, (s1 : Int, s2 : Int) => (s1+s2)/2)
	}

	class Firm(val rd: ResearchAndDevelopment, val hr: HumanResources, val p: Production, val s: Sales, val f: Finance) {
		val health = Apply(rd.state, hr.state, p.state, s.state, f.state, (s1 : Int, s2 : Int, s3 : Int, s4 : Int, s5 : Int) => (s1+s2+s3+s4+s5)/5)
	}

	def main(args: Array[String]) {
		val rd = new ResearchAndDevelopment()
		val hr = new HumanResources()
		val p = new Production(rd, hr)
		val s = new Sales(p)
		val f = new Finance(hr, s)
		val firm = new Firm(rd, hr, p, s, f)
		
		
		val alg = Importance(100, firm.health)
		alg.start()
		alg.stop()
		println( alg.probability(firm.health, 50))
	}
}