import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene._
import scalafx.stage._
import scalafx.scene.control.{Button}
import scalafx.scene.layout.StackPane

object BookExamples extends JFXApp {
  
  stage = new JFXApp.PrimaryStage {
    title = "The Solar System"
    
    val button = new Button("Hello World")
    val pane = new StackPane
    pane.getChildren add button 
    
    scene = new Scene(pane)
    width = 800
    height = 600
  }
}