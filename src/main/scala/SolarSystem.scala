import scala.collection.mutable.ArrayBuffer
import scalafx.scene.paint.Color

object SolarSystem {
  val bodies = new ArrayBuffer[Body]();
  
  bodies += new Body("Sun", 1988550000, 696342, 0, Color.Yellow)
  bodies += new Body("Mercury", 2439.7, 330.2, 46, Color.White)
  bodies += new Body("Venus", 6051.8, 4868.5, 107, Color.Yellow)
  bodies += new Body("Earth", 6371, 5973.6, 147, Color.Blue)
  bodies += new Body("Mars", 3389.5, 641.85, 205, Color.Red)
  bodies += new Body("Jupiter", 1898600, 69911, 741, Color.Red)
  bodies += new Body("Saturn", 568460, 58232, 1350, Color.Brown)
  bodies += new Body("Uranus", 86832, 25362, 2750, Color.LightGreen)
  bodies += new Body("Neptune", 102430, 24622, 4450, Color.DarkGreen)
}