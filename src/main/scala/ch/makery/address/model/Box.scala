package ch.makery.address.model

import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text, TextAlignment}
import scalafx.scene.paint.Color
import scalafx.scene.layout.StackPane

class Box extends StackPane(){
    var square = new Rectangle(){
        width = 100
        height = 100
        arcHeight = 10
        arcWidth = 10
    }
    var digit = new Text(""){
        font = Font.font(".Apple SD Gothic Neol", 36)
        textAlignment = TextAlignment.CENTER
        wrappingWidth = 100
    }

    this.getChildren().addAll(square, digit)

    def define(value: Int)={
        var d = value.toString
        value match {
            case 0 => square.fill = Color.rgb(204,192,178)
                        digit.fill = Color.TRANSPARENT
            case 2 => square.fill = Color.rgb(238,228,218)
                        digit.fill = Color.rgb(120,111,98)
            case 4 => square.fill = Color.rgb(236,224,200)
                        digit.fill = Color.rgb(120,111,98)
            case 8 => square.fill = Color.rgb(242,177,121)
                        digit.fill = Color.rgb(255,255,255)
            case 16 => square.fill = Color.rgb(245,149,99)
                        digit.fill = Color.rgb(255,255,255)
            case 32 => square.fill = Color.rgb(245,124,95)
                        digit.fill = Color.rgb(255,255,255)
            case 64 => square.fill = Color.rgb(244,94,60)
                        digit.fill = Color.rgb(255,255,255)
            case 128 => square.fill = Color.rgb(238,206,115)
                        digit.fill = Color.rgb(255,255,255)
            case 256 => square.fill = Color.rgb(237,205,97)
                        digit.fill = Color.rgb(255,255,255)
            case 512 => square.fill = Color.rgb(236,199,82)
                        digit.fill = Color.rgb(255,255,255)
            case 1024 => square.fill = Color.rgb(237,197,63)
                        digit.fill = Color.rgb(255,255,255)
            case 2048 => square.fill = Color.rgb(238,194,46)
                        digit.fill = Color.rgb(255,255,255)
            case _ => square.fill = Color.rgb(238,194,46)
                        digit.fill = Color.rgb(255,255,255)
        }
        digit.text = d  
    }
}