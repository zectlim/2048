package ch.makery.address

import scalafx.application.JFXApp 
import scalafx.application.JFXApp.PrimaryStage 
import scalafx.scene.Scene 
import scalafx.Includes._ 
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import javafx.{scene => jfxs} 

object MainApp extends JFXApp { 
  val rootResource = getClass.getResource("view/Layout.fxml") 
  val loader = new FXMLLoader(rootResource, NoDependencyResolver) 
  loader.load();  
  val roots = loader.getRoot[jfxs.layout.AnchorPane] 
  
  stage = new PrimaryStage { 
    title = "2048" 
    scene = new Scene { 
      root = roots
    } 
  }
}