package frc.team4069.saturn.lib

import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.experimental.command.CommandScheduler
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import frc.team4069.saturn.lib.commands.SaturnSubsystem
import frc.team4069.saturn.lib.commands.SubsystemHandler
import frc.team4069.saturn.lib.mathematics.units.SIUnit
import frc.team4069.saturn.lib.mathematics.units.Second
import frc.team4069.saturn.lib.mathematics.units.milli

const val kLanguageKotlin = 6

abstract class SaturnRobot(val period: SIUnit<Second> = 20.milli.second) {

    protected val wrappedValue = WpiTimedRobot()

    protected inner class WpiTimedRobot : TimedRobot(period.value) {
        init {
            HAL.report(FRCNetComm.tResourceType.kResourceType_Language, kLanguageKotlin)
        }

        override fun robotInit() {
            this@SaturnRobot.robotInit()
            LiveWindow.disableAllTelemetry()
            SubsystemHandler.lateInit()
        }

        override fun autonomousInit() {
            this@SaturnRobot.autonomousInit()
            SubsystemHandler.autoReset()
        }

        override fun teleopInit() {
            this@SaturnRobot.teleopInit()
            SubsystemHandler.teleopReset()
        }

        override fun testInit() {
            this@SaturnRobot.testInit()
        }

        override fun disabledInit() {
            this@SaturnRobot.disabledInit()
            SubsystemHandler.setNeutral()
        }

        override fun robotPeriodic() {
            this@SaturnRobot.robotPeriodic()
            CommandScheduler.getInstance().run()
        }

        override fun autonomousPeriodic() {
            this@SaturnRobot.autonomousPeriodic()
        }

        override fun teleopPeriodic() {
            this@SaturnRobot.teleopPeriodic()
        }

        override fun testPeriodic() {
            this@SaturnRobot.testPeriodic()
        }

        override fun disabledPeriodic() {
            this@SaturnRobot.disabledPeriodic()
        }
    }

    protected open fun robotInit() {}
    protected open fun autonomousInit() {}
    protected open fun teleopInit() {}
    protected open fun testInit() {}
    protected open fun disabledInit() {}

    protected open fun robotPeriodic() {}
    protected open fun autonomousPeriodic() {}
    protected open fun teleopPeriodic() {}
    protected open fun testPeriodic() {}
    protected open fun disabledPeriodic() {}

    open operator fun SaturnSubsystem.unaryPlus() {
        SubsystemHandler.add(this)
    }

    fun start() {
        RobotBase.startRobot { wrappedValue }
    }
}