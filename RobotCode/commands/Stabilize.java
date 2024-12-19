// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PID;
import frc.robot.subsystems.Sensor;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Stabilize extends CommandBase {
    private PID loop;
    private double kP1;
    private double kP2;
    private DriveTrain m_DriveTrain;
    private Sensor tiltSensor;
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Stabilize(DriveTrain m_DriveTrain, double kP1, double kP2, Sensor tiltSensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_DriveTrain = m_DriveTrain;
    this.tiltSensor = tiltSensor;
    this.kP1 = kP1;
    this.kP2 = kP2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    loop = new PID(m_DriveTrain, kP1, kP2, tiltSensor);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  //BR
  public void execute() {
   // SmartDashboard.putNumber("Sensor Value", Robot.ExampleSubsystem.returnSensorOutput());
   if (Math.abs(tiltSensor.get()) < loop.getSetpoint()-6 || Math.abs(tiltSensor.get()) > loop.getSetpoint()+6){
    if (tiltSensor.get() > loop.getSetpoint()+6)
      m_DriveTrain.drive(-.25, -.25);
    else 
      m_DriveTrain.drive(.25, .25);
  }
  else if (loop.getSetpoint() != loop.getEncoderValue()){
    m_DriveTrain.drive(loop.encoderOutput(), loop.encoderOutput());
  }
  else
    m_DriveTrain.drive(0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

/*
 * Sample code for stabilization
 * 
 * if (!autoBalanceXMode && (Math.abs(pitchAngleDegrees) >= Math.abs(kOffBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = true;
        } else if (autoBalanceXMode && (Math.abs(pitchAngleDegrees) <= Math.abs(kOonBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = false;
        }
        if (!autoBalanceYMode && (Math.abs(pitchAngleDegrees) >= Math.abs(kOffBalanceAngleThresholdDegrees))) {
            autoBalanceYMode = true;
        } else if (autoBalanceYMode && (Math.abs(pitchAngleDegrees) <= Math.abs(kOonBalanceAngleThresholdDegrees))) {
            autoBalanceYMode = false;
        }

        // Control drive system automatically,
        // driving in reverse direction of pitch/roll angle,
        // with a magnitude based upon the angle

        if (autoBalanceXMode) {
            double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
            xAxisRate = Math.sin(pitchAngleRadians) * -1;
        }
        if (autoBalanceYMode) {
            double rollAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
            yAxisRate = Math.sin(rollAngleRadians) * -1;
        }

        try {
            myRobot.driveCartesian(xAxisRate, yAxisRate, stick.getTwist(), 0);
        } catch (RuntimeException ex) {
            String err_string = "Drive system error:  " + ex.getMessage();
            DriverStation.repo
 */