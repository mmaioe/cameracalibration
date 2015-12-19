import mmaioe.com.cameracalibration.Homography
import mmaioe.com.image.Image

/**
 * Created by maoito on 12/19/15.
 */
object Test {
  def main(args: Array[String]) {
    val image = new Image("/Users/maoito/DSC01018.JPG")

    println(image.height()+","+image.width())


    val homography = Homography.solve(
      new {val x:Double = 0; val y:Double=0; },
      new {val x:Double = 0; val y:Double=5; },
      new {val x:Double = 5; val y:Double=0; },
      new {val x:Double = 5; val y:Double=5; },
      new {val x:Double = 0; val y:Double=0; },
      new {val x:Double = 2; val y:Double=6; },
      new {val x:Double = 4; val y:Double= -1; },
      new {val x:Double = 6; val y:Double=4; }
    )


    homography.foreach{
      line =>
        line.foreach{
          element =>
            print(element+",")
        }
        println()
    }
    val newImage = new Image(image.width()*2,image.height()*2)
//
//
    val base_x=900
    val base_y=900
    (0 until image.width()).foreach{
      x =>
        (0 until image.height()).foreach{
          y =>
            val new_point = Homography.multiply(homography,List(x,y))

            if(0 <= new_point(0)+base_x && new_point(0)+base_x < newImage.width() && 0 <= new_point(1)+base_y && new_point(1)+base_y < newImage.height()){
              newImage.setColor(new_point(0)+base_x,new_point(1)+base_y,image.getRGBColor(x,y))
            }
        }
    }

    newImage.writeImage("homographied.png")
  }
}