/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.auto.AethiaCenterThreeCells;
import frc.robot.subsystems.Shooter;



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
  private static Button shooterTeleop;

  //motors 
  public static CANSparkMax shooterMotorTop;
  public static CANSparkMax shooterMotorBottom;

  //sensors
  private static RelativeEncoder shooterTopEnc;
  private static RelativeEncoder shooterBottomEnc;

  //subsystems
  private static Shooter shooter;

  private static SparkMaxPIDController pidcontrol_shooter_top;
  private static SparkMaxPIDController pidcontrol_shooter_btm;

  public static PowerDistribution pdp;

  //camera
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {

    shooterMotorTop = new CANSparkMax(Constants.SHOOTER_MOTOR_TOP, MotorType.kBrushless);
    shooterMotorBottom = new CANSparkMax(Constants.SHOOTER_MOTOR_BOTTOM, MotorType.kBrushless);

    pidcontrol_shooter_top = shooterMotorTop.getPIDController();
    pidcontrol_shooter_btm = shooterMotorBottom.getPIDController();
    // Configure the button bindings

    shooterTopEnc = shooterMotorTop.getEncoder();
    shooterBottomEnc = shooterMotorBottom.getEncoder();

    pdp = new PowerDistribution(0, ModuleType.kCTRE);


    

    shooterMotorBottom.setInverted(false);

    
    shooterMotorTop.setInverted(true);

    shooter = new Shooter(shooterMotorTop, shooterMotorBottom, shooterTopEnc, shooterBottomEnc, pidcontrol_shooter_top, pidcontrol_shooter_btm);
    //shooter.setDefaultCommand(new MoveShooterTeleop(Constants.SHOOTER_TELEOP_SPEED));



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
    shooterTeleop = new JoystickButton(joy, Constants.SHOOTER_TELEOP);

    shooterTeleop.whileHeld(new MoveShooterTeleop(Constants.SHOOTER_TELEOP_SPEED));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() 
  {
    return new AethiaCenterThreeCells();
  }
  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  
}