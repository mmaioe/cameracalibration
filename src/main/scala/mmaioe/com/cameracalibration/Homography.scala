package mmaioe.com.cameracalibration

import mmaioe.com.linearsystem.LinearSolver

/**
 * Created by maoito on 12/19/15.
 */
object Homography {
  /**
   *
   * p1_* belongs to the samge image
   * p2_* belongs to the same image
   *
   * p1_i and p2_i should be a pair of corresponding points
   * @param p1_1
   * @param p1_2
   * @param p1_3
   * @param p1_4
   * @param p2_1
   * @param p2_2
   * @param p2_3
   * @param p2_4
   */
  def solve(
             p1_1: {val x:Double; val y:Double; },
             p1_2: {val x:Double; val y:Double; },
             p1_3: {val x:Double; val y:Double; },
             p1_4: {val x:Double; val y:Double; },
             p2_1: {val x:Double; val y:Double; },
             p2_2: {val x:Double; val y:Double; },
             p2_3: {val x:Double; val y:Double; },
             p2_4: {val x:Double; val y:Double; }
             ):List[List[Double]]={
    val homography = LinearSolver.solveWithSquareMatrix(
      Array(
        Array(p1_1.x, p1_1.y, 1, 0,      0,      0, -p1_1.x*p2_1.x, -p1_1.y*p2_1.x),
        Array(0,      0,      0, p1_1.x, p1_1.y, 1, -p1_1.x*p2_1.y, -p1_1.y*p2_1.y),
        Array(p1_2.x, p1_2.y, 1, 0,      0,      0, -p1_2.x*p2_2.x, -p1_2.y*p2_2.x),
        Array(0,      0,      0, p1_2.x, p1_2.y, 1, -p1_2.x*p2_2.y, -p1_2.y*p2_2.y),
        Array(p1_3.x, p1_3.y, 1, 0,      0,      0, -p1_3.x*p2_3.x, -p1_3.y*p2_3.x),
        Array(0,      0,      0, p1_3.x, p1_3.y, 1, -p1_3.x*p2_3.y, -p1_3.y*p2_3.y),
        Array(p1_4.x, p1_4.y, 1, 0,      0,      0, -p1_4.x*p2_4.x, -p1_4.y*p2_4.x),
        Array(0,      0,      0, p1_4.x, p1_4.y, 1, -p1_4.x*p2_4.y, -p1_4.y*p2_4.y)
      )
      ,
      Array(p2_1.x,p2_1.y,p2_2.x,p2_2.y,p2_3.x,p2_3.y,p2_4.x,p2_4.y)
    )

    return List[List[Double]](
      List(homography(0),homography(1),homography(2)),
      List(homography(3),homography(4),homography(5)),
      List(homography(6),homography(7),1)
    )
  }

  def multiply(homography:List[List[Double]], point: List[Double]): List[Int] ={
    var threePoint:collection.mutable.ListBuffer[Double] = collection.mutable.ListBuffer[Double]()++point
    threePoint += 1

    val new_point:collection.mutable.ListBuffer[Int] = collection.mutable.ListBuffer[Int]()
    (0 until 2).foreach{
      i =>
        var sum = 0.0
        (0 until 3).foreach{
          j =>
            sum += homography(i)(j) * threePoint(j);
        }
        if(sum - sum.toInt > 0.5) sum = sum.toInt+1
        else                      sum = sum.toInt

        new_point += sum.toInt
    }

    return new_point.toList
  }
}
