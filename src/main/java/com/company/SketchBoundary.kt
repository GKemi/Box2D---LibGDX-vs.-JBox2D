package com.company

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.ChainShape
import hackemi.box2d.LibGDXBox2DProcessing
import processing.library.template.Sketch

class SketchBoundary(box2d: LibGDXBox2DProcessing) {

    init {
        val chain = ChainShape()

        val surface = mutableListOf<Vector2>()
        val sketchWidth = Sketch.width.toFloat()
        val sketchHeight = Sketch.height.toFloat()

        surface.add(Vector2(0f, 0f))
        surface.add(Vector2(0f, sketchHeight))
        surface.add(Vector2(sketchWidth, sketchHeight))
        surface.add(Vector2(sketchWidth, 0f))

        val vertices = arrayOfNulls<Vector2>(4)

        for (i in 0 until surface.size) {
            vertices[i] = box2d.coordPixelsToWorld(surface[i])
        }

        chain.createChain(vertices)

        val bd = BodyDef()
        val body = box2d.world.createBody(bd)
        body.createFixture(chain, 1f)
    }
}