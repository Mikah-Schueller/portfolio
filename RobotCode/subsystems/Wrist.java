package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase{
    CANSparkMax WristSpark;
    private RelativeEncoder m_encoder;
    private static double startingPos;
    
    /** Creates a new DriveTrain. */
    public Wrist() {
      WristSpark = new CANSparkMax(Constants.Wrist12, MotorType.kBrushless);

      WristSpark.restoreFactoryDefaults();
      WristSpark.setIdleMode(IdleMode.kBrake);
      m_encoder = WristSpark.getEncoder();
    }
  
    public void spin(double speed){
      WristSpark.set(speed);
      SmartDashboard.putNumber("Wrist Position:", getEncoderPos());
    }

    public double getEncoderPos(){
      return m_encoder.getPosition();
    }
  
    public void set(){
      startingPos = m_encoder.getPosition();
    }
  
    public double getStartingPos(){
      return startingPos;
    }
  
    public void stop(){
      spin(0);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
