// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ExtendoArm extends SubsystemBase {
  CANSparkMax inNOutSpark;
  CANSparkMax outMotor;
 
  /** Creates a new Climber. */
  public ExtendoArm() {
    inNOutSpark = new CANSparkMax(Constants.ExtendoArm15, MotorType.kBrushless);
    outMotor = new CANSparkMax(Constants.ExtendoArm22, MotorType.kBrushless);

    inNOutSpark.setIdleMode(IdleMode.kBrake);
    outMotor.setIdleMode(IdleMode.kBrake);
  }

  public void extend(double speed){
    inNOutSpark.set(speed); 
    outMotor.set(speed);
  }

  public void retractClimber(double speed){
    extend(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
