// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
//import frc.robot.subsystems.CompressorControl;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DefaultTeleopDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain runCodeRun;
  private Joystick driver;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DefaultTeleopDrive(DriveTrain runCodeRun, Joystick driver) {
    this.runCodeRun = runCodeRun;
    this.driver = driver;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(runCodeRun);



  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double right = driver.getRawAxis(Constants.DRIVER_RIGHT_Y) * -1 * Constants.DRIVE_SPEED;
    double left = driver.getRawAxis(Constants.DRIVER_LEFT_Y) * -1 * Constants.DRIVE_SPEED;
    runCodeRun.drive(left, right);
   
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //runCodeRun.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
