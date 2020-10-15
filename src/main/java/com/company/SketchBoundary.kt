package com.company

import org.jbox2d.collision.shapes.ChainShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.BodyDef
import processing.library.template.Sketch
import shiffman.box2d.Box2DProcessing

class SketchBoundary(box2d: Box2DProcessing) {

    init {
        val chain = ChainShape()

        val surface = mutableListOf<Vec2>()
        val sketchWidth = Sketch.width.toFloat()
        val sketchHeight = Sketch.height.toFloat()

        surface.add(Vec2(0f, 0f))
        surface.add(Vec2(0f, sketchHeight))
        surface.add(Vec2(sketchWidth, sketchHeight))
        surface.add(Vec2(sketchWidth, 0f))

        val vertices = arrayOfNulls<Vec2>(4)

        for (i in 0 until surface.size) {
            vertices[i] = box2d.coordPixelsToWorld(surface[i])
        }

        chain.createChain(vertices, vertices.size)

        val bd = BodyDef()
        val body = box2d.world.createBody(bd)
        body.createFixture(chain, 1f)
    }
}