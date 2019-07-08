package frc.team4069.saturn.lib.motor.ctre

import com.ctre.phoenix.motorcontrol.IMotorController
import frc.team4069.saturn.lib.mathematics.units.Key
import frc.team4069.saturn.lib.mathematics.units.NativeUnit
import frc.team4069.saturn.lib.mathematics.units.SIUnit
import frc.team4069.saturn.lib.mathematics.units.nativeunits.*
import frc.team4069.saturn.lib.motor.AbstractSaturnEncoder
import kotlin.properties.Delegates

class SaturnCTREEncoder<T: Key>(
        val motorController: IMotorController,
        val pidIdx: Int = 0,
        model: NativeUnitModel<T>
) : AbstractSaturnEncoder<T>(model) {
    override val rawVelocity: SIUnit<NativeUnitVelocity>
        get() = motorController.getSelectedSensorVelocity(pidIdx).STUPer100ms

    override val rawPosition: SIUnit<NativeUnit>
        get() = motorController.getSelectedSensorPosition(pidIdx).STU

    var encoderPhase by Delegates.observable(false) { _, _, newValue ->
        motorController.setSensorPhase(newValue)
    }

    override fun resetPosition(newPosition: SIUnit<T>) {
        motorController.setSelectedSensorPosition(model.toNativeUnitPosition(newPosition).value.toInt(), pidIdx, 0)
    }
}