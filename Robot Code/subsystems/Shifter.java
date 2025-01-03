// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shifter extends SubsystemBase {
  private Solenoid shiftSolenoid;
  
  /** Creates a new Shifter. */
  public Shifter() {
    shiftSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
  }

  public void setLowGear(){
    shiftSolenoid.set(true);
  }

  public void setHighGear(){
    shiftSolenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
