 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.TurnRight;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmHigh;
import frc.robot.commands.ArmLow;
import frc.robot.commands.ArmMid;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.ArmStabilize2;
import frc.robot.commands.ArmStartingConfig;
import frc.robot.commands.ArmDown;
import frc.robot.commands.AutonomousDriveStraight;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.TakeInCone;
import frc.robot.commands.TakeInCube;
import frc.robot.commands.WaveUp;
import frc.robot.commands.WristStabilize;
import frc.robot.commands.WaveDown;
import frc.robot.commands.DefaultTeleopDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.LimelightActivate;
import frc.robot.commands.ShiftGears;
import frc.robot.commands.Stabilize;
import frc.robot.commands.TurnLeft;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ExtendoArm;
import frc.robot.subsystems.IntakeSpinner;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.WhackAB2;
import frc.robot.subsystems.Wrist;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain runCodeRun = new DriveTrain();
  private final Shifter shifter = new Shifter();
  private final Wrist wrist = new Wrist();
  private final ExtendoArm arm = new ExtendoArm();
  private final WhackAB2 climber = new WhackAB2();
  private final Sensor tilitSensor = new Sensor();
  private final Limelight limelight = new Limelight();
  private final IntakeSpinner spinner = new IntakeSpinner();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick driver =
      new Joystick(Constants.kDriverControllerPort);
  private final Joystick operator =
      new Joystick(Constants.kOperatorControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureButtonBindings();
    runCodeRun.setDefaultCommand(new DefaultTeleopDrive(runCodeRun, driver));
    //CameraServer.startAutomaticCapture();
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureButtonBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new POVButton(driver, 0).whileTrue(new DefaultDrive(runCodeRun, Constants.DRIVE_SPEED));
    new POVButton(driver, 180).whileTrue(new DefaultDrive(runCodeRun, -Constants.DRIVE_SPEED));
    new POVButton(driver, 90).whileTrue(new TurnRight(runCodeRun));
    new POVButton(driver, 270).whileTrue(new TurnLeft(runCodeRun));

    new JoystickButton(driver, Constants.DRIVER_BUTTON_LB).toggleOnTrue(new ShiftGears(shifter)); 
    new JoystickButton(driver, Constants.DRIVER_BUTTON_A).whileTrue(new LimelightActivate(runCodeRun, limelight));
    new JoystickButton(driver, Constants.DRIVER_BUTTON_X).whileTrue(new Stabilize(runCodeRun, 0.01, 0.01, tilitSensor));

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    new POVButton(operator, 0).toggleOnTrue(new ArmHigh(climber));
    new POVButton(operator, 180).toggleOnTrue(new ArmStartingConfig(climber));
    new POVButton(operator, 90).toggleOnTrue(new ArmMid(climber));
    new POVButton(operator, 270).toggleOnTrue(new ArmLow(climber));

    new JoystickButton(operator, Constants.OPERATOR_BUTTON_A).toggleOnTrue(new TakeInCube(spinner));
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_B).toggleOnTrue(new TakeInCone(spinner));
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_X).whileTrue(new ArmExtend(arm));
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_Y).whileTrue(new ArmRetract(arm));
    
   JoystickButton LB = new JoystickButton(operator, Constants.OPERATOR_BUTTON_LB);
   new JoystickButton(operator, Constants.OPERATOR_BUTTON_LB).whileTrue(new ArmUp(climber));
   
   if (!LB.getAsBoolean()){
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_RB).whileTrue(new ArmDown(climber));
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_RB).whileFalse(new ArmStabilize2(climber));
   }

   JoystickButton RT = new JoystickButton(operator, Constants.OPERATOR_BUTTON_RT);
   new JoystickButton(operator, Constants.OPERATOR_BUTTON_RT).whileTrue(new WaveDown(wrist));
   
   if (!RT.getAsBoolean()){
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_LT).whileTrue(new WaveUp(wrist));
    new JoystickButton(operator, Constants.OPERATOR_BUTTON_LT).whileFalse(new WristStabilize(wrist));
   }
    
    //new JoystickButton(operator, Constants.OPERATOR_BUTTON_START).toggleOnTrue(new ArmStabilize());
    
    



    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //driver.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    //return new ExampleCommand(m_exampleSubsystem);

    return //(new TakeInCube(spinner).withTimeout(2).andThen
    (new AutonomousDriveStraight(runCodeRun, -0.5).withTimeout(7));
  }
  
}
