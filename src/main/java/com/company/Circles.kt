package com.company

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import hackemi.box2d.LibGDXBox2DProcessing
import processing.library.template.Sketch

class Circles(var bodies: MutableList<Body> = mutableListOf(),
              var r: Float = 5f,
              val box2d: LibGDXBox2DProcessing) {

    fun add(x: Float, y: Float) {
        val body = createSandParticleBody(x, y, r)
        bodies.add(body)
    }

    fun display() = Sketch.run {

        for (body in bodies) {
            // We look at each body and get its screen position
            val pos = box2d.getBodyPixelCoord(body)
            // Get its angle of rotation
            val a = body.angle
            pushMatrix()
            translate(pos.x, pos.y)
            rotate(-a)
            fill(254f, 202f, 32f, 150f)
            noStroke()
            ellipse(0f, 0f, r * 2, r * 2)
            popMatrix()
        }
    }

    private fun createSandParticleBody(x: Float, y: Float, radius: Float): Body {
        val bd = BodyDef()

        bd.position.set(box2d.coordPixelsToWorld(x, y))
        bd.type = BodyDef.BodyType.DynamicBody
        val particleBody = box2d.world.createBody(bd)

        // Make the body's shape a circle
        val cs = CircleShape()
        cs.radius = box2d.scalarPixelsToWorld(radius)

        val fd = FixtureDef()
        fd.shape = cs
        // Parameters that affect physics
        fd.density = 1f
        fd.friction = 0.01f
        fd.restitution = 0.3f

        // Attach fixture to body
        particleBody.createFixture(fd)
        var velocityY = 10f

        // Give it a random initial velocity (and angular velocity)
        particleBody.linearVelocity = Vector2(Sketch.random(-10f, 10f), velocityY)
        particleBody.angularVelocity = Sketch.random(-10f, 10f)

        return particleBody
    }
}