package org.clustering4ever.clustering.models
/**
 * @author Beck Gaël
 */
import org.clustering4ever.math.distances.{GenericDistance, Distance}
import org.clustering4ever.clustering.GenericClusteringModel
import org.clustering4ever.vectors.GVector
/**
 *
 */
trait MetricModel[V <: GVector[V], D <: Distance[V]] extends GenericClusteringModel {
	/**
	 * A metric defined on any object which inherit GVector
	 */
	val metric: D
}