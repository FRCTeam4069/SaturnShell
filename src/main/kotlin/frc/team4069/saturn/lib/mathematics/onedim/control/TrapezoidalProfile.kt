package frc.team4069.saturn.lib.mathematics.onedim.control

import frc.team4069.saturn.lib.mathematics.units.Length
import frc.team4069.saturn.lib.mathematics.units.Time
import frc.team4069.saturn.lib.mathematics.units.derivedunits.LinearAcceleration
import frc.team4069.saturn.lib.mathematics.units.derivedunits.LinearVelocity
import frc.team4069.saturn.lib.util.DeltaTime
import kotlin.math.pow

class TrapezoidalProfile private constructor(
        val distance: Double,
        val maxVelocity: Double,
        val maxAcceleration: Double
) : IKinematicController {

    constructor(distance: Length, maxVelocity: LinearVelocity, maxAcceleration: LinearAcceleration)
            : this(distance.meter, maxVelocity.value, maxAcceleration.value)

    private var cruiseVelocity = maxVelocity

    private val tAccel: Double
    private val xAccel: Double

    private val tCruise: Double
    private val xCruise: Double

    init {
        tAccel = (maxVelocity / maxAcceleration).let {
            if (x(it, 0.0, 0.0, maxAcceleration) < distance / 2) {
                it
            } else {
                cruiseVelocity = maxAcceleration * Math.sqrt(distance / maxAcceleration)
                cruiseVelocity / maxAcceleration
            }
        }
        xAccel = x(tAccel, 0.0, 0.0, maxAcceleration)

        tCruise = ((distance - (2 * xAccel)) / cruiseVelocity).coerceAtLeast(0.0)
        xCruise = x(tCruise, 0.0, cruiseVelocity, 0.0)
    }

    val t1 = tAccel
    val t2 = tAccel + tCruise
    val t3 = 2 * tAccel + tCruise

    // Loops
    private var dt = -1.0
    private var elapsed = 0.0

    private val deltaTime = DeltaTime()

    override fun getVelocity(time: Time): PVAData {
        val dt = deltaTime.updateTime(time).second
        elapsed += dt

        return when {
            elapsed < t1 -> {
                val t = elapsed
                PVAData(
                        x(t, 0.0, 0.0, maxAcceleration),
                        v(t, 0.0, maxAcceleration),
                        maxAcceleration)
            }
            elapsed < t2 -> {
                val t = elapsed - t1
                PVAData(
                        x(t, xAccel, cruiseVelocity, 0.0),
                        v(t, cruiseVelocity, 0.0),
                        0.0
                )
            }
            elapsed < t3 -> {
                val t = elapsed - t2
                PVAData(
                        x(t, xAccel + xCruise, cruiseVelocity, -maxAcceleration),
                        v(t, cruiseVelocity, -maxAcceleration),
                        -maxAcceleration
                )
            }
            else -> PVAData(distance, 0.0, 0.0)
        }
    }

    private fun x(t: Double, x0: Double, v0: Double, a: Double) = x0 + v0 * t + 0.5 * a * t.pow(2)
    private fun v(t: Double, v0: Double, a: Double) = v0 + a * t
}