// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WhackAB2 extends SubsystemBase {
  private CANSparkMax armSpark;
  private RelativeEncoder m_encoder;
  private CANSparkMax armSpark2;
  private static double startingPos;
  /** Creates a new Climber. */
  public WhackAB2() {
    //armSpark2 = new CANSparkMax(Constants.ArmPort2, MotorType.kBrushless);

    //armSpark2.follow(armSpark, true);
    armSpark = new CANSparkMax(Constants.WhackAB24, MotorType.kBrushless);
    armSpark2 = new CANSparkMax(Constants.WhackAB13, MotorType.kBrushless);

    armSpark2.follow(armSpark, true);
    armSpark.restoreFactoryDefaults();
    armSpark.setIdleMode(IdleMode.kBrake);
    m_encoder = armSpark.getEncoder();
    //startingPos = m_encoder.getPosition();
    SmartDashboard.putNumber("Saved Position:", startingPos);
  }

  public double getEncoderPos(){
    return m_encoder.getPosition();
  }

  public void set(){
    startingPos = m_encoder.getPosition();
    SmartDashboard.putNumber("Saved Position:", startingPos);
  }

  public void set(double num){
    startingPos = num;
    SmartDashboard.putNumber("Saved Position:", startingPos);
  }

  public void display(){
    SmartDashboard.putNumber("Saved Position:", startingPos);
    SmartDashboard.putNumber("Current Position:", m_encoder.getPosition());
  }

  public double getStartingPos(){
    return startingPos;
  }

  public void extend(double speed){
    armSpark.set(speed); 
  }

  public void stop(){
    armSpark.set(0);
  }

  public void retractClimber(double speed){
    extend(-speed);
  }

  @Override
  public void periodic() {
    
  }
}
