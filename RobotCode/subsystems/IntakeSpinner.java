package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSpinner extends SubsystemBase{
  CANSparkMax SpinIt;
    
    /** Creates a new DriveTrain. */
    public IntakeSpinner() {
      SpinIt = new CANSparkMax(Constants.SpinIntake23, MotorType.kBrushless);
      SpinIt.setIdleMode(IdleMode.kBrake);
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    public void spin(double speed){
      SpinIt.set(speed);
    }
    
  
    public void stop(){
      spin(0);
    }
}
