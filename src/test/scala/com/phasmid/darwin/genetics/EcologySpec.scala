package com.phasmid.darwin.genetics

import org.scalatest.{FlatSpec, Matchers}

import scala.util._

/**
  * Created by scalaprof on 5/6/16.
  */
class EcologySpec extends FlatSpec with Matchers {

  val adapter: Adapter[Double, Double] = new AbstractAdapter[Double, Double] {
    def matchFactors(f: Factor, t: Trait[Double]): Try[(Double, FunctionShape[Double, Double])] = f match {
      case Factor("elephant grass") => t.characteristic.name match {
        case "height" => Success(t.value, Fitness.inverseDelta)
        case _ => Failure(new GeneticsException(s"no match for factor: ${t.characteristic.name}"))
      }
    }
  }

  def fitnessFunction(t: Double, functionType: FunctionShape[Double, Double], x: Double): Fitness = functionType match {
    case FunctionShape(s, f) => f(t, x)
    case _ => throw new GeneticsException(s"ecoFitness does not implement functionType: $functionType")
  }

  "apply" should "work" in {
    val height = Characteristic("height")
    val phenotype: Phenotype[Double] = Phenotype(Seq(Trait(height, 2.0)))
    val elephantGrass = Factor("elephant grass")
    val ecology: Ecology[Double, Double] = Ecology("test", Map("height" -> elephantGrass), fitnessFunction, adapter)
    val adaptatype: Adaptatype[Double] = ecology(phenotype)
    val adaptations = adaptatype.adaptations
    adaptations.size shouldBe 1
    adaptations.head should matchPattern { case Adaptation(Factor("elephant grass"), _) => }
    val fitness = adaptations.head.ecoFitness(EcoFactor(elephantGrass, 1.6))
    fitness should matchPattern { case Success(Fitness(_)) => }
    fitness.get.x shouldBe 0.0
  }
}