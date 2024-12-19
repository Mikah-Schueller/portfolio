// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WhackAB2;

public class ArmUp extends CommandBase {
  WhackAB2 m_climber;
  /** Creates a new UnClim. */
  public ArmUp(WhackAB2 climber) {
    m_climber = climber;
    addRequirements(m_climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_climber.getEncoderPos() < 30){
      m_climber.retractClimber(-.3);
      m_climber.display();
    }
    else if (m_climber.getEncoderPos() > 30){
      m_climber.retractClimber(-.1);
      m_climber.display();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.retractClimber(0);
    m_climber.set();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
