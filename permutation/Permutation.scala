object Permutation {
  
  //Back tracking permutation
  //If permutation(seq, N-1) can generate all permutations, by swaping N and previous N-1 elements
  //There'd be N*permutation(seq, N-1) permutations, which results in N! in total
  def backTrackPermutation[T](seq: Array[T]) = {
    def permutation0(seq: Array[T], boundIndex: Int): Unit = {
      if (boundIndex == 0) println(seq.mkString)
      for (i <- 0 to boundIndex) {
        swap(seq, i, boundIndex)
        permutation0(seq, boundIndex-1)
        swap(seq, i, boundIndex)
      }
    }
    permutation0(seq, seq.length-1)
  }
  
  //Heap's permutation algorithm
  def heapPermutation[T](seq: Array[T]) = {
    def permutation0(seq: Array[T], boundIndex: Int): Unit = {
      if (boundIndex == 0) println(seq.mkString)
      for (i <- 0 to boundIndex) {
        permutation0(seq, boundIndex-1)
        swap(seq, boundIndex % 2 * i, boundIndex)
      }
    }
    permutation0(seq, seq.length-1)
  }
  
  def swap[T](seq: Array[T], a: Int, b: Int) = {
    val t = seq(a)
    seq(a) = seq(b)
    seq(b) = t
  }
}
