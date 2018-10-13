package frc.team4069.saturn.lib.math.profile

import edu.wpi.first.wpilibj.Timer
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt


/**
 * A trapezoidal motion profile.
 * Consists of vel ramp up, cruise, and vel ramp down periods.
 * For more information, refer to 254 champs video.
 *
 * @param targetPos Target position
 * @param maxVel Maximum velocity
 * @param maxAccel Maximum acceleration
 * @constructor Constructor
 */
class TrapezoidalMP(
    private val targetPos: Double,
    private val maxVel: Double,
    private val maxAccel: Double,
    freq: Double = 50.0
) {

    enum class ProfileState { REST, ACCEL, CRUISE, DECEL }

    var tAccel = maxVel / maxAccel
    var xAccel = 0.5 * maxAccel * tAccel.pow(2)

    var xCruise = 0.0
    var tCruise = 0.0

    val isTrapezoidal: Boolean

    private val dt = 1 / freq

    init {
        if (2 * xAccel < targetPos) {
            // Trapezoidal
            isTrapezoidal = true
            xCruise = targetPos - 2 * xAccel
            tCruise = xCruise / maxVel
        } else {
            // Triangular
            isTrapezoidal = false
            xAccel = targetPos / 2.0
            tAccel = sqrt(xAccel / maxAccel)
        }
    }

    var state = ProfileState.REST

    private var error = 0.0
    private var integral = 0.0
    private val pvajData = PVAJData()
    private val sign = sign(targetPos)

    var isFinished = false

    fun reset() {
        state = ProfileState.REST

        error = 0.0
        integral = 0.0
        pvajData.apply {
            pos = 0.0
            vel = 0.0
            accel = 0.0
        }
    }

    private fun updateState(curPos: Double) {
        state = when {
            abs(curPos) < xAccel -> ProfileState.ACCEL
            abs(curPos) < xAccel + xCruise -> ProfileState.CRUISE
            abs(curPos) < 2 * xAccel + xCruise || pvajData.vel > 0.0 -> ProfileState.DECEL
            else -> ProfileState.REST
        }
    }

    fun follow(curPos: Double): PVAJData {
        updateState(curPos)
        when (state) {
            ProfileState.ACCEL -> {
                pvajData.accel = maxAccel * sign
                pvajData.pos += pvajData.vel * dt + 0.5 * pvajData.accel * dt.pow(2) * sign
                pvajData.vel += maxAccel * dt * sign
            }

            ProfileState.CRUISE -> {
                pvajData.accel = 0.0
                pvajData.pos += pvajData.vel * dt * sign
            }

            ProfileState.DECEL -> {
                pvajData.accel = -maxAccel * sign
                pvajData.pos += pvajData.vel * dt + 0.5 * pvajData.accel * dt.pow(2) * sign
                pvajData.vel -= maxAccel * dt * sign
            }

            ProfileState.REST -> isFinished = true
        }
        return pvajData
    }
}
