package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    private static final com.ctre.phoenix.motorcontrol.TalonFXControlMode TalonFXControlMode = null;
    private static com.ctre.phoenix.motorcontrol.TalonFXControlMode PercentOutput = null;
    Limelight limelight;
    TalonFX RightMotor1;
    TalonFX LeftMotor2;
    TalonFX LeftMotor3;
    TalonFX RightMotor4;
    TalonFX RightMotor5;
    TalonFX LeftMotor6; 
    

    
    /** Creates a new DriveTrain. */
    public DriveTrain() {
      RightMotor1 = new TalonFX(Constants.RightFalcon1);
      LeftMotor2 = new TalonFX(Constants.LeftFalcon2);
      LeftMotor3 = new TalonFX(Constants.LeftFalcon3);
      RightMotor4 = new TalonFX(Constants.RightFalcon4); 
      RightMotor5 = new TalonFX(Constants.RightFalcon5); 
      LeftMotor6 = new TalonFX(Constants.LeftFalcon6);
      

    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    public void drive(double leftSpeed, double rightSpeed){
      RightMotor1.set(TalonFXControlMode.PercentOutput, -1.29*rightSpeed);
      LeftMotor2.set(TalonFXControlMode.PercentOutput, leftSpeed);
      LeftMotor3.set(TalonFXControlMode.PercentOutput, leftSpeed);
      RightMotor4.set(TalonFXControlMode.PercentOutput, -1.29*rightSpeed);
      RightMotor5.set(TalonFXControlMode.PercentOutput, -1.29*rightSpeed);
      LeftMotor6.set(TalonFXControlMode.PercentOutput, -leftSpeed);
    }

    public double getFalconPosition(){
      return LeftMotor3.getSelectedSensorPosition();
    }
    
  
    public void straightForward(){
      drive(Constants.DRIVE_SPEED, Constants.DRIVE_SPEED);
      if(limelight.getLimelightDistance() != 0.0){
      while(limelight.getLimelightDistance() != 0.0){
        drive(Constants.DRIVE_SPEED, Constants.DRIVE_SPEED);
      }
    }
  }
    
  
    public void straightBackward(){
      drive(-Constants.DRIVE_SPEED, -Constants.DRIVE_SPEED);
    }
  
    public void turnRight(){
      drive(Constants.DRIVE_SPEED, -Constants.DRIVE_SPEED);
    }
  
    public void turnLeft(){
      drive(-Constants.DRIVE_SPEED, Constants.DRIVE_SPEED);
    }
  
    public void stop(){
      drive(0,0);
    }
}
