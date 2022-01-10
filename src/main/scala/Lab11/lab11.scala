import com.cra.figaro.language._
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._

import scala.collection.immutable.Range
import scala.reflect.api.Universe

object Lab11
{

	def main(args: Array[String]){
		val citizenPresident = Flip(0.000000025)

		val citizenLeftHanded = Flip(0.1)
		val presidentLeftHanded = Flip(0.5)
		citizenLeftHanded.observe(true)
		val leftHandedCitizenBecomesPresident = VariableElimination.probability(citizenLeftHanded, 40000000 * citizenLeftHanded)
		println("Probability that someone became president given that they're left-handed: " + leftHandedCitizenBecomesPresident)

		val presidentToHarvard = Flip(0.15)
		val citizenToHarvard = Flip(0.0005)
		citizenToHarvard.observe(true)
		val HarvardCitizenBecomesPresident = VariableElimination.probability(citizenToHarvard, 40000000 * citizenToHarvard)
		println("Probability that someone became president given that they went to Harvard: " + HarvardCitizenBecomesPresident)

		citizenToHarvard.observe(true)
		citizenLeftHanded.observe(true)
		presidentToHarvard.observe(true)
		val harvard_and_lefthanded_citizen = VariableElimination.probability(leftHandedCitizenBecomesPresident, 40000000 * citizenToHarvard * citizenLeftHanded)
		println("Probability that someone became president given that they went to Harvard and are left-handed: " + harvard_and_lefthanded_citizen)
		
	}
}
