package org.clustering4ever.clustering.kcenters.scala
/**
 * @author Beck Gaël
 */
import scala.language.higherKinds
import scala.reflect.ClassTag
import scala.collection.{immutable, GenSeq}
import org.clustering4ever.math.distances.{Distance, BinaryDistance}
import org.clustering4ever.math.distances.binary.Hamming
import org.clustering4ever.clusterizables.{Clusterizable, EasyClusterizable}
import org.clustering4ever.util.ScalaCollectionImplicits._
import org.clustering4ever.vectors.{GVector, BinaryVector}
import org.clustering4ever.clustering.ClusteringAlgorithmLocalBinary
/**
 * The famous K-Means using a user-defined dissmilarity measure.
 * @param data GenSeq of Clusterizable descendant, the EasyClusterizable is the basic reference
 * @param k number of clusters seeked
 * @param minShift The stopping criteria, ie the distance under which centers are mooving from their previous position
 * @param maxIterations maximal number of iteration
 * @param metric a defined binary dissimilarity measure on a GVector descendant
 */
final case class KModes[D <: BinaryDistance](final val k: Int, final val metric: D, final val minShift: Double, final val maxIterations: Int, final val customCenters: immutable.HashMap[Int, BinaryVector] = immutable.HashMap.empty[Int, BinaryVector]) extends KCentersAncestor[BinaryVector, D, KModesModel[D]] with ClusteringAlgorithmLocalBinary[KModesModel[D]] {

	final val algorithmID = org.clustering4ever.extensibleAlgorithmNature.KModes

	final def fit[O, Cz[B, C <: GVector[C]] <: Clusterizable[B, C, Cz], GS[X] <: GenSeq[X]](data: GS[Cz[O, BinaryVector]]): KModesModel[D] = KModesModel(k, metric, minShift, maxIterations, obtainCenters(data))
}
/**
 *
 */
object KModes {
	/**
	 *
	 */
	final def generateAnyAlgorithmArgumentsCombination[D <: BinaryDistance](
		kValues: Seq[Int] = Seq(4, 6, 8),
		metricValues: Seq[D] = Seq(Hamming),
		minShiftValues: Seq[Double] = Seq(0.0001),
		maxIterationsValues: Seq[Int] = Seq(100),
		initializedCentersValues: Seq[immutable.HashMap[Int, BinaryVector]] = Seq(immutable.HashMap.empty[Int, BinaryVector])): Seq[KModes[D]] = {
		for(
			k <- kValues;
			metric <- metricValues;
			minShift <- minShiftValues;
			maxIterations <- maxIterationsValues;
			initializedCenters <- initializedCentersValues
		) yield	KModes(k, metric, minShift, maxIterations, initializedCenters)
	}
	/**
	 * Run the K-Modes with any binary distance
	 */
	final def fit[D <: BinaryDistance, GS[Y] <: GenSeq[Y]](
		data: GS[Array[Int]],
		k: Int,
		metric: D,
		maxIterations: Int,
		minShift: Double
	): KModesModel[D] = {
		KModes(k, metric, minShift, maxIterations).fit(binaryToClusterizable(data))
	}
}