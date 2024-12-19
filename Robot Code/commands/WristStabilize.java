// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

/** An example command that uses an example subsystem. */
public class WristStabilize extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Wrist wrist;
  private double start;
  private double current;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WristStabilize(Wrist climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    wrist = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //arm.set();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  //BR
  public void execute() {
   // SmartDashboard.putNumber("Sensor Value", Robot.ExampleSubsystem.returnSensorOutput());
   start = (int)(wrist.getStartingPos()*10)/10.0;
   current = (int)(wrist.getEncoderPos()*10)/10.0;
   if (current > start){
    wrist.spin(Math.abs(start-current)*-0.1);
   }
   else if (current < start){
    wrist.spin(Math.abs(start-current)*0.1);
   }
   else if (current == start){
    wrist.stop();
   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wrist.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
