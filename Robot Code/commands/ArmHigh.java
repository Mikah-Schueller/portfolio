// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WhackAB2;

/** An example command that uses an example subsystem. */
public class ArmHigh extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private WhackAB2 arm;
  private double start;
  private double current;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmHigh(WhackAB2 climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    arm = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    arm.set(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  //BR
  public void execute() {
   // SmartDashboard.putNumber("Sensor Value", Robot.ExampleSubsystem.returnSensorOutput());
   start = (int)(arm.getStartingPos()*10)/10.0;
   current = (int)(arm.getEncoderPos()*10)/10.0;

   if (current > start){
    arm.retractClimber(0);
    arm.display();
   }
   else if (current < start){
    arm.extend(0.3);
    arm.display();
   }
   else if (current == start){
    arm.stop();
    arm.display();
   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
