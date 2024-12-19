// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PID extends SubsystemBase {
    private double kP1;
    private double kP2;
    private Sensor tiltSensor;
    private double encoderSetpoint;
    private DriveTrain drive;
  /** Creates a new ExampleSubsystem. */
  public PID(DriveTrain m_DriveTrain, double kP1, double kP2, Sensor tiltSensor) {
    this.tiltSensor = tiltSensor;
    this.kP1 = kP1;
    this.kP2 = kP2;
    drive = m_DriveTrain;
    encoderSetpoint = getEncoderValue(); //may need to replace 'getEncoderValue()' with '-88.5'
    SmartDashboard.putNumber("Tilt:", tiltSensor.get());
  }

 public double getError(){
    return tiltSensor.getSetpoint()-(int)(tiltSensor.get()*10)/10;
 }

 public double getEncoderValue(){
  return (int)(drive.getFalconPosition()*10)/10;
 }

 public double getSetpoint(){
  return encoderSetpoint;
 }

 public double output(){
  return kP1*getError();
 }

 public double getEncoderError(){
  return getSetpoint()-getEncoderValue();
  }

  public double encoderOutput(){
    return kP2*getEncoderError();
  }

  
}
