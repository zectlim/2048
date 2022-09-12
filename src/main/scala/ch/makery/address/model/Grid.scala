package ch.makery.address.model

import scalafx.scene.layout.GridPane
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.RowConstraints
import scalafx.geometry.{Pos, Insets}

class Grid() {
    val grid = new GridPane(){
        hgap = 10
        vgap = 10   
        margin = Insets(50,50,50,50) 
    } 
    for (i <- 0 to 3){
        grid.getColumnConstraints.addAll(new ColumnConstraints(100))
        grid.getRowConstraints.addAll(new RowConstraints(100))
    }
}