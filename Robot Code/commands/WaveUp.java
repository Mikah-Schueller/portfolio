// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Wrist;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class WaveUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Wrist wrist;
  private final Sensor tiltSensor;
 

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WaveUp(Wrist wrist) {
    this.wrist = wrist;
    tiltSensor = new Sensor();
   
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //runCodeRun.drive(speed, speed);
    if (SmartDashboard.getNumber("Tilt:", tiltSensor.get()) < 25)
      wrist.spin(-0.4);
    else{
      if (wrist.getEncoderPos() > -2.25)
        wrist.spin(-0.4);
      else 
        wrist.spin(0.3);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wrist.stop();
    wrist.set();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
