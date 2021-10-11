import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound.CPD

object Ex {
    def main(args: Array[String]) {
        // Model
        val reducere = Flip(0.5)
        val nevoie = Flip(0.5)
		val v1 = Flip(1.0)
		val v2 = Flip(0.5)
		val v3 = Flip(0.8)
		val v4 = Flip(0.2)
		
        val result = CPD(reducere,nevoie,
            (true, true) -> Flip(1.0),
            (true, false) -> Flip(0.5),
            (false, true) -> Flip(0.8),
            (false, false) -> Flip(0.2)
        )
        
        // Algo Inferenta
        val algorithm = Importance(1000, nevoie)
        algorithm.start()
        
        // Interogam modelul
        println(algorithm.probability(nevoie, true))
	
    }
}