package org.clustering4ever.preprocessing
/**
 * @author Beck Gaël
 */
import scala.language.higherKinds
import org.clustering4ever.extensibleAlgorithmNature._
import org.clustering4ever.clustering.CollectionNature
/**
 * The basic trait shared by all clustering models
 */
trait Preprocessing[Collection[_]] extends CollectionNature[Collection] {
	/**
	 * General methods to obtain a clustering from the model in order to measure performances scores
	 */
	// def preprocess[ID, V <: Seq[_], Sz[X, Y <: Seq[_]] <: DFCLG[X, Y]](data: Collection[Sz[ID, V]])(implicit ct: ClassTag[Sz[ID, V]]): Collection[Sz[ID, V]]
}
/**
 * Neccessary preprocessing algorithm arguments to launch it 
 */
trait PreprocessingArgs extends Serializable {
	val algorithm: PreprocessingAlgorithmNature
}
