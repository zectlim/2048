package ch.makery.address

import ch.makery.address.model.Content
import ch.makery.address.model.Grid
import ch.makery.address.model.Box
import scalafxml.core.macros.sfxml

import scalafx.scene.layout.{GridPane, StackPane, ColumnConstraints, RowConstraints}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Text, Font, FontWeight, TextAlignment}
import scalafx.scene.paint.Color
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.Stage

import scalafx.Includes._
import scala.collection.mutable.ArrayBuffer
import util.Random
import java.io.{FileWriter, File}
import scala.io.Source 
import scala.io.StdIn.{readLine,readInt} 

@sfxml
class Controller (var board: StackPane, var scoreDisplay: Text, var bestDisplay: Text, var playMode: Text){


var mode :Boolean = true
var bScore = ""
var bMove = ""

if (new File ("src/main/resources/ch/makery/address/best.txt").exists == true){
    val readFrom = Source.fromFile("src/main/resources/ch/makery/address/best.txt")
    val extract = readFrom.getLines().toList
    readFrom.close
    val getBest = extract(0).split(",").toList
    bScore = getBest(0)
    bMove = getBest(1)
    
} else {
    val best = new FileWriter(new File ("src/main/resources/ch/makery/address/best.txt"))
    
}

val back = new Grid().grid
    for (i <- 0 to 3; j <- 0 to 3){
        var bgBox = new Box()
        bgBox.define(0)
        back.add(bgBox, i, j)
    }

val front = new Grid().grid

board.children = List(
    new Rectangle() {
        width = 500
        height = 500
        arcHeight = 10
        arcWidth = 10
        fill = Color.rgb(188,174,161)
    },
    back,
    front
    )

front.focusTraversable = true
front.requestFocus()

playMode.font= Font.font("Arial", FontWeight.BOLD, 20)
playMode.textAlignment = TextAlignment.CENTER
playMode.wrappingWidth = 120
playMode.fill = Color.White
if (mode == true){
playMode.text = "SCORE"
} else {
    playMode.text = "MOVE"
}

scoreDisplay.font= Font.font("Arial", FontWeight.BOLD, 20)
scoreDisplay.textAlignment = TextAlignment.CENTER
scoreDisplay.wrappingWidth = 120
scoreDisplay.fill = Color.White
scoreDisplay.text ="0"

bestDisplay.font= Font.font("Arial", FontWeight.BOLD, 20)
bestDisplay.textAlignment = TextAlignment.CENTER
bestDisplay.wrappingWidth = 120
bestDisplay.fill = Color.White
if (mode == true){
bestDisplay.text = bScore
} else {
    bestDisplay.text = bMove
}

    var run = new Content(front)

    front.onKeyPressed = (key: KeyEvent) => {
        key.code match {

            case KeyCode.RIGHT => 
            run.shiftRight()
            display()
            detect2048(run.trace)
            case KeyCode.LEFT => 
            run.shiftLeft()
            display()
            detect2048(run.trace)
            case KeyCode.UP =>
            run.shiftUp()
            display()
            detect2048(run.trace)
            
            case KeyCode.DOWN =>
            run.shiftDown()
            display()
            detect2048(run.trace)
            case _ => println("invalid key")
    }
}

def detect2048(t: Int): Unit= {
    val tile = t
    if (tile >= 2048 && mode != true){
        if (run.move < bMove.toInt){
        val record = new FileWriter(new File ("src/main/resources/ch/makery/address/best.txt"))
        record.write(bScore + "," + (scoreDisplay.text).toString)
        record.close
        }
    val alert = new Alert(Alert.AlertType.Information){ 
          initOwner(MainApp.stage) 
          title = "Congragulation!!!" 
          headerText = ("You have reach 2048 in " + (run.move).toString + "!!!/n Your previous best move is " + bMove)
          contentText = ("You can continue playing by clicking okay button")
        }
    val result = alert.showAndWait()
    result match {
        case Some(ButtonType.OK) => 
        case _ => newGame()
        }
    }
}

def display(): Unit = {
    if (mode == true){
            scoreDisplay.text = (run.score).toString
            } else {
                scoreDisplay.text = (run.move).toString
            }
            if (run.score > bScore.toInt) {
                bestDisplay.text = (run.score).toString
            }
}

def end(): Unit={
    if (mode == true && run.score > bScore.toInt) {
        val record = new FileWriter(new File ("src/main/resources/ch/makery/address/best.txt"))
        record.write((run.score).toString + "," + bMove)
        record.close
    }

    scoreDisplay.text ="0"
}

def newGame()= {
    end()
    run = new Content(front)
}
def swap()={
    end()
    mode = !mode
    run = new Content(front)
    
    if (mode == true) {
        run.move = -1
        playMode.text = "SCORE"
        bestDisplay.text = bScore
    } else {run.score = -1
        playMode.text = "MOVE"
        bestDisplay.text = bMove
    }

    }
}