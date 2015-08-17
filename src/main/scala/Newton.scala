import scalafx.Includes._
import scala.math.random
import scalafx.animation._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene._
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.{Sphere, Box}
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Translate
import scalafx.beans.property.DoubleProperty
import scalafx.scene.input.MouseEvent
import scala.collection.mutable.ArrayBuffer
import scalafx.animation.Timeline.INDEFINITE

object Newton extends JFXApp {
  
  stage = new JFXApp.PrimaryStage {
    title = "The Solar System"
    
    scene = new Scene(1900, 1000, true, SceneAntialiasing.Balanced) {
      
      fill = Color.Black
      
      val planets = new Group();
      
      val scaler = 100000;
      
      for (body <- SolarSystem.bodies) {
        val zMove = -(body.distanceMillionKm * 1000000) / scaler / 10
        val radius = if (body.name == "Sun") body.radiusKm / scaler else body.radiusKm / scaler * 100;

        println("Adding " + body.name + " with radius of " + radius + " and " + zMove + " Z Offset" )
        
        val sphere = new Sphere(radius) {
          material = new PhongMaterial {
            diffuseColor = body.color
            specularColor = Color.AntiqueWhite
          }
          translateZ = zMove
        }
        
        planets.getChildren.add(sphere)
      }
      
      planets.rotationAxis = Rotate.XAxis;
      planets.rotate = -0.03
      
      val light = new PointLight {
        color = Color.AntiqueWhite
        translateX = -265
        translateY = -260
        translateZ = -100000
      }

      root = new Group {
        
        translateX = 950
        translateY = 500
        translateZ = 1200
        
        rotationAxis = Rotate.YAxis
      }
      
      content = new Group(planets, light)
      
      camera = new PerspectiveCamera(false)
      
      addMouseInteraction(this, planets)
      
      new Timeline {
        cycleCount = INDEFINITE
        autoReverse = true
        keyFrames = (for (planet <- planets.getChildren) yield Seq(
            at(0 s) {Set(planet.translateZ -> random * 5000)},
            at(5 s) {Set(planet.translateZ -> random * 5000)}
        )).flatten
      }.play
      
    }
  }
  
  /** Add mouse interaction to a scene, rotating given node. */
  private def addMouseInteraction(scene: Scene, node: Node) {
    val angleY = DoubleProperty(-50)
    val yRotate = new Rotate {
      angle <== angleY
      axis = Rotate.YAxis
    }
    var anchorX: Double = 0
    var anchorAngleY: Double = 0

    node.transforms = Seq(yRotate)

    scene.onMousePressed = (event: MouseEvent) => {
      anchorX = event.sceneX
      anchorAngleY = angleY()
    }
    scene.onMouseDragged = (event: MouseEvent) => {
      angleY() = anchorAngleY + anchorX - event.sceneX
    }
  }
}