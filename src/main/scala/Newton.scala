import scalafx.Includes._
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

object Newton extends JFXApp {
  
  stage = new JFXApp.PrimaryStage {
    title = "The Solar System"
    scene = new Scene(1000, 1000, true, SceneAntialiasing.Balanced) {
      
      fill = Color.Black
      
      val planets = new Group();
      
      val scaler = 10000;
      
      for (body <- SolarSystem.bodies) {
        println("Adding " + body.name + " with radius of " + body.radiusKm / scaler)
        
        val radius = if (body.name == "Sun") body.radiusKm / scaler else body.radiusKm / scaler * 50;
        
        val sphere = new Sphere(radius) {
          material = new PhongMaterial {
            diffuseColor = body.color
            specularColor = Color.AntiqueWhite
          }
          translateZ = -(body.distanceMillionKm * 1000000) / scaler
        }
        
        planets.getChildren.add(sphere)
      }
      
      planets.rotationAxis = Rotate.XAxis;
      planets.rotate = -0.03
      
      val light = new PointLight {
        color = Color.AntiqueWhite
        translateX = -265
        translateY = -260
        translateZ = -300
      }

      root = new Group {
        
        translateX = 500
        translateY = 500
        translateZ = 500
        
        rotationAxis = Rotate.YAxis

      }
    
      content = new Group(planets, light)
      
      camera = new PerspectiveCamera(false)
      
      addMouseInteraction(this, planets)
      
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