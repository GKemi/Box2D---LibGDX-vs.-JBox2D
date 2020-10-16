package processing.library.template

import com.company.Circles
import com.company.SketchBoundary
import hackemi.box2d.LibGDXBox2DProcessing
import processing.core.PApplet
import processing.core.PConstants

object Sketch : PApplet(){
    init {
        this.runSketch()
    }

    override fun settings() {
        super.settings()
        size(1000, 1000, PConstants.P2D)
        pixelDensity(2)
    }

    lateinit var box2d: LibGDXBox2DProcessing
    lateinit var circles: Circles

    override fun setup() {
        background(0)
        setupBox2D()
        circles = Circles(box2d  = this.box2d)
    }

    override fun draw() {
        background(0)

        box2d.step()
        if (mousePressed) circles.add(mouseX.toFloat(), mouseY.toFloat())

        displayDebugData()
        drawCircles()
    }

    private fun displayDebugData() {
        stroke(255f)
        text("Framerate: ${frameRate}", 10f, 10f)
        text("No. Particles: ${circles.bodies.size}", 10f, 20f)
    }

    private fun setupBox2D() {
        box2d = LibGDXBox2DProcessing(this)
        box2d.createWorld()
        box2d.setGravity(0f, -10f)
        SketchBoundary(box2d)
    }

    fun drawCircles() {
        circles.display()
    }

}

fun main() {
    Sketch
}