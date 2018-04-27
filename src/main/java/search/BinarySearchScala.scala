package search

import scala.annotation.tailrec

object BinarySearchScala {
  /**
   * Academic implementation as in Java or C.
   * If the value exists in the array, return value would be Some(index), else wise None would be returned.
   * The type T of the array's element should implement Ordered[T] or Comparable[T] in Java.
   * Or at least it can be coerced to Ordered[T] (such as value types like Int).
   */
  def search1[T <% Ordered[T]](array: Array[T], value: T): Option[Int] = {
    var left = 0
    var right = array.size - 1
    var middle = -1
     
    while (left <= right) {
      middle = left + (right - left >> 1)
      if (array(middle) > value) right = middle - 1
      else if (array(middle) < value) left = middle + 1
      else return Some(middle)
    }
    None
  }
  
  /**
   * Recursively find between left and right.
   * The function recursiveSearch is tail recursive, so it's guaranteed that the same method stack would be utilized.
   */
  def search2[T <% Ordered[T]](array: Array[T], value: T): Option[Int] = {
    @tailrec
    def recursiveSearch(left: Int, right: Int): Option[Int] = (left + (right - left >> 1)) match {
      case _ if left >= right => None 
      case middle if array(middle) > value => recursiveSearch(left, middle - 1)
      case middle if array(middle) < value => recursiveSearch(middle + 1, right)
      case middle => Some(middle)
    }
    recursiveSearch(0, array.size - 1)
  }
}
