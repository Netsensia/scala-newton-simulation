import scalafx.scene.paint.Color
import scalafx.scene.shape.{Sphere}

class Body(
    _name: String, 
    _massKg: Double, 
    _radiusKm: Double, 
    _distanceMillionKm: Double,
    _color: Color
    ) {
  
  val name = _name
  val massKg = _massKg
  val radiusKm = _radiusKm
  val distanceMillionKm = _distanceMillionKm
  val color = _color
  
}