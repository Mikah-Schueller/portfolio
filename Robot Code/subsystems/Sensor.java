// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

//use this class to access the tilt sensor data and put its output in the get() method

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sensor extends SubsystemBase {
  
  private double readout;
  AHRS ahrs;

  public Sensor() {
    ahrs = new AHRS(SPI.Port.kMXP);
  }

public double get(){
  //return device.readout();
  readout = ahrs.getRoll();
  SmartDashboard.putNumber("Current Tilt:", readout);
  return readout;
} 

public double getSetpoint(){
  return -91.5;
} 



}

