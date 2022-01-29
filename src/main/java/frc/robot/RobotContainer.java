/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.cscore.UsbCamera;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...

  //numbers

  //joystick
  private static Joystick joy;

  //motors 
  private final MotorController frontLeft, rearLeft;
  private final MotorController frontRight,rearRight;
  private static MotorControllerGroup leftDrive;
  private static MotorControllerGroup rightDrive;

  //sensors
  private static AHRS ahrs;

  //subsystems
  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;

  //camera
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  { 

    frontLeft = new WPI_VictorSPX(Constants.LEFT_TOP_MOTOR);
    rearLeft = new WPI_VictorSPX(Constants.LEFT_BOTTOM_MOTOR);
    leftDrive = new MotorControllerGroup(frontLeft, rearLeft);
    frontRight = new WPI_VictorSPX(Constants.RIGHT_BOTTOM_MOTOR);
    rearRight = new WPI_VictorSPX(Constants.RIGHT_TOP_MOTOR);
    rightDrive = new MotorControllerGroup(frontRight, rearRight);
    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);
    driveTrain = new DriveTrain(leftDrive, rightDrive, drive);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    ahrs = new AHRS(SPI.Port.kMXP);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    joy = new Joystick(0);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() 
  {
    return null;
  }

  public static Joystick getJoy() {return joy;}
  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static MotorControllerGroup getLeftSCG(){return leftDrive;}
  public static MotorControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  }