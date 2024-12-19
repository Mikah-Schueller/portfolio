package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ta;
  NetworkTableEntry ty;
  NetworkTableEntry tv;
  NetworkTableEntry ledMode;
  
  public Limelight(){
    table = NetworkTableInstance.getDefault().getTable("limelight-miakah"); 
    tx = table.getEntry("tx");
    ta = table.getEntry("ta");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ledMode = table.getEntry("ledMode");
  }

  public double getLimelightDistance(){
    return 65 / Math.tan(Math.toRadians(getYoffset()));
  }

  public double getXoffset(){
    return tx.getDouble(0.0);
  }

  public double getTarget(){
    return tv.getDouble(0);
  }

  public double getYoffset(){
    return ty.getDouble(0.0);
  }

  public double getAreaTarget(){
    return ta.getDouble(0.0);
  }

  public void setLimelightOn(){
    ledMode.setNumber(3);
  }

  public void setLimelightOff(){
    ledMode.setNumber(1);
  }

  public void setLimelightBlink(){
    ledMode.setNumber(2);
  }
}
