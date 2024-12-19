package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;
//import frc.robot.subsystems.Shooter;

public class LimelightActivate extends CommandBase{
  public DriveTrain driveTrain;
  public Limelight limelight;
  //public Shooter shooter;

  /** Creates a new LimelightShoot. */
  public LimelightActivate(DriveTrain d, Limelight l /*, Shooter s*/) {
    driveTrain = d;
    limelight = l;
    //shooter = s;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //double xOffset = limelight.getXoffset();
    if (limelight.getTarget() == 1){
      if (limelight.getAreaTarget() < 1.5){
        if(limelight.getXoffset() > 2)
          driveTrain.drive(0.2,0);
        else if (limelight.getXoffset() < -1.5)
          driveTrain.drive(0,0.2);
        else
          driveTrain.drive(.2,.2);
      }
      else
        driveTrain.drive(0,0);
      
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
    
}
