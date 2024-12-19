// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

/** An example command that uses an example subsystem. */
public class AutonomousDriveStraight extends CommandBase {
  private final DriveTrain m_driveTrain;
  private double speed;

  /**
   * 
   */
  public AutonomousDriveStraight(DriveTrain runCodeRun, double speed) {
    m_driveTrain = runCodeRun;
    this.speed = speed;

    addRequirements(m_driveTrain);

  }

  /**
   * Creates a new ExampleCommand.
   *
   * The subsystem used by this command.
   */

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.drive(speed, speed);
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
