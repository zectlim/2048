package ch.makery.address.model

import ch.makery.address.model.Box
import scalafx.animation.ScaleTransition
import scalafx.scene.layout.GridPane
import scalafx.util.Duration
import scala.collection.mutable.ArrayBuffer
import util.Random


class Content(gridPane: GridPane){
    var activeGrid = gridPane
    activeGrid.getChildren().clear
    var content = Array(
        Array(0, 0, 0 ,0),
        Array(0, 0, 0 ,0),
        Array(0, 0, 0 ,0),
        Array(0, 0, 0 ,0)
        )
    var value, score, move, trace = 0

    addRandomBox(3)

def addRandomBox (number: Int): Unit = {
    val n = number
    var counter = 0
        while (counter != n){
            var col = Random.nextInt(3)
            var row = Random.nextInt(3)
                if (content(row)(col)== 0){
                val chance = Random.nextInt(10)
                var value = if (chance < 8) 2 else 4
                content(row)(col) = value               
                var box = new Box()
                box.define(value)
                activeGrid.add(box, col, row)
                val popOut = new ScaleTransition(new Duration(400), box) {
                    fromX = 0.1
                    fromY = 0.1
                    toY = 1
                    toX = 1
                }
                popOut.play()
                counter += 1
                }
            }
        }

def generateBox(): Unit = {
        activeGrid.getChildren().clear
        for (row <- 0 to 3; col <- 0 to 3)
            if (content(row)(col) != 0) {
                value = content(row)(col)            
                var box = new Box()
                box.define(value)
                activeGrid.add(box, col, row)
            }
        }

def shiftUp() = {
    var fromColumn = ArrayBuffer[Int]()
    for (y <- 0 to 3){
        fromColumn.clear
    for (x <- 0 to 3){
        fromColumn = fromColumn :+ content(x)(y)
    }
    var temp = fromColumn.filter(_ != 0)
        if (temp.length > 1){
            for (i <- 0 until temp.length -1){
                if (temp(i) == temp(i + 1)){
                    temp(i) = temp(i)*2
                    temp(i+1) = 0
                if (score != -1){
                    score += temp(i)
                }
                if (move != -1) {
                move += 1
                }
                trace = temp(i)
            } 
        }
        temp = temp.filter(_ != 0)
        }
        while (temp.length != 4) {
              temp = temp :+ 0
            }
        for (x <- 0 to 3){
        content(x)(y) = temp(x)
        }
    }
    generateBox()
    addRandomBox(1)
}

def shiftDown() = {
    var fromColumn = ArrayBuffer[Int]()
    for (y <- 0 to 3){
        fromColumn.clear 
        for (x <- 0 to 3){
        fromColumn = fromColumn :+ content(x)(y)
    }
    var temp = fromColumn.filter(_ != 0)
        if (temp.length > 1){
            for (i <- 0 until temp.length -1){
                if (temp(i) == temp(i + 1)){
                    temp(i) = temp(i)*2
                    temp(i+1) = 0
                if (score != -1){
                    score += temp(i)
                }
                if (move != -1) {
                move += 1
                }
                trace = temp(i)
            } 
        }
        temp = temp.filter(_ != 0)
        }
        while (temp.length != 4) {
              temp = ArrayBuffer(0) ++ temp
            }
        for (x <- 0 to 3){
        content(x)(y) = temp(x)
        }
    }
    generateBox()
    addRandomBox(1)
}

def shiftLeft() = {
    for (x <- 0 to 3){
        var temp = content(x).filter(_ != 0)
        if (temp.length > 1){
        for (i <- 0 until temp.length -1){
            if (temp(i) == temp(i + 1)){
            temp(i) = temp(i)*2
            temp(i+1) = 0
        if (score != -1){
            score += temp(i)
                }
        if (move != -1) {
            move += 1
            }
        trace = temp(i)
            } 
        }
       
        temp = temp.filter(_ != 0)
        }
        while (temp.length != 4) {
                temp = temp :+ 0
            }
       
        for (y <- 0 to 3){
        content(x)(y) = temp(y)
            }
        }

        generateBox()
        addRandomBox(1)
    }

    def shiftRight() = {
        for (x <- 0 to 3){
        var temp = content(x).filter(_ != 0)
            if (temp.length > 1){
        for (i <- 0 until temp.length -1){
            if (temp(i) == temp(i + 1)){
                temp(i) = temp(i)*2
                temp(i+1) = 0
            if (score != -1){
                score += temp(i)
            }
            if (move != -1) {
                move += 1
            }
            trace = temp(i)
            } 
        }
        temp = temp.filter(_ != 0)
            }
      while (temp.length != 4) {
            temp = Array(0) ++ temp
            }
      for (y <- 0 to 3){
        content(x)(y) = temp(y)
            }
        }
        generateBox()
        addRandomBox(1)
    }

}