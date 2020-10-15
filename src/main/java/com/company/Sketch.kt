package processing.library.template

import processing.core.PApplet
import processing.core.PConstants

object Sketch : PApplet(){
    init {
        this.runSketch()
    }

    override fun settings() {
        super.settings()
        size(400, 400, PConstants.P2D)
    }

    override fun setup() {
        background(0)
        rectMode(PConstants.CENTER)
    }

    override fun draw() {
        rect(width/2f, height/2f, width*0.8f, height*0.8f)
    }

}

fun main() {
    Sketch
}