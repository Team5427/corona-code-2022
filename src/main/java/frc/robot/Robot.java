/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.VisionTurn;
import frc.robot.subsystems.DriveTrain;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;
  
  NetworkTable table;
  // PhotonCamera ballCamera;
  // PhotonTrackedTarget ballTarget;
  
  public static double pitch;
  public static double yaw;
  public static double skew;
  public static double area;
  public static double PixelX;
  public static double PixelY;
  public static boolean hasTarget;

  NetworkTable table2;
  // public PhotonCamera targetCamera;
  // public PhotonTrackedTarget targetTarget;
  
  public static double pitch2;
  public static double yaw2;
  public static double skew2;
  public static double area2;
  public static double PixelX2;
  public static double PixelY2;
  public static boolean hasTarget2;
  double default_all = 0.0;

  private RobotContainer m_robotContainer;

  public static double turn_rbt_deg;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    m_robotContainer = new RobotContainer();
    RobotContainer.getAHRS().reset();
    DriveTrain.leftSpeed = 0;
    DriveTrain.rightSpeed = 0;
    
    // ballCamera = new PhotonCamera("photoncam2");

    NetworkTableInstance PIInstance = NetworkTableInstance.create();
    PIInstance.setServer("ballvision");
    PIInstance.startClient();
    table = PIInstance.getTable("photonvision").getSubTable("photoncam2");

    // targetCamera = new PhotonCamera("photoncam");

    NetworkTableInstance PIInstance2 = NetworkTableInstance.create();
    PIInstance2.setServer("targetvision");
    PIInstance2.startClient();
    table2 = PIInstance2.getTable("photonvision").getSubTable("photoncam");

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    if (RobotContainer.getJoy().getRawButton(7))
    {
      CommandScheduler.getInstance().schedule(new MoveTransport(0.5));
    }

    hasTarget = table.getEntry("hasTarget").getBoolean(true);
    pitch = table.getEntry("targetPitch").getDouble(default_all);
    yaw = table.getEntry("targetYaw").getDouble(default_all);
    skew = table.getEntry("targetSkew").getDouble(default_all);
    area = table.getEntry("targetArea").getDouble(default_all);
    PixelX = table.getEntry("targetPixelsX").getDouble(default_all);
    PixelY = table.getEntry("targetPixelsY").getDouble(default_all);

    hasTarget2 = table2.getEntry("hasTarget").getBoolean(true);
    pitch2 = table2.getEntry("targetPitch").getDouble(default_all);
    yaw2 = table2.getEntry("targetYaw").getDouble(default_all);
    skew2 = table2.getEntry("targetSkew").getDouble(default_all);
    area2 = table2.getEntry("targetArea").getDouble(default_all);
    PixelX2 = table2.getEntry("targetPixelsX").getDouble(default_all);
    PixelY2 = table2.getEntry("targetPixelsY").getDouble(default_all);

    turn_rbt_deg = ((RobotContainer.getJoy().getRawAxis(3) * 180) + 180);

    // if(ballCamera.getLatestResult().hasTargets()){
    //   hasTarget = ballCamera.getLatestResult().hasTargets();
    //   ballTarget = ballCamera.getLatestResult().getBestTarget();
    //   pitch = ballTarget.getPitch();
    //   yaw = ballTarget.getYaw();
    //   skew = ballTarget.getSkew();
    //   area = ballTarget.getArea();
    // }
    // PixelX = table.getEntry("targetPixelsX").getDouble(default_all);
    // PixelY = table.getEntry("targetPixelsY").getDouble(default_all);

    // hasTarget2 = targetCamera.getLatestResult().hasTargets();

    // if(targetCamera.getLatestResult().hasTargets()){
    //   hasTarget2 = targetCamera.getLatestResult().hasTargets();
    //   targetTarget = targetCamera.getLatestResult().getBestTarget();
    //   pitch2 = targetTarget.getPitch();
    //   yaw2 = targetTarget.getYaw();
    //   skew2 = targetTarget.getSkew();
    //   area2 = targetTarget.getArea();
    // }

    SmartDashboard.putBoolean("HasTarget", hasTarget);
    // SmartDashboard.putBoolean("Running", VisionTurn.isRunning);

    //SmartDashboard.putNumber("Yaw", RobotContainer.getAHRS().getYaw());
    SmartDashboard.putNumber("degrees", Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360)));
    SmartDashboard.putNumber("angle", RobotContainer.getAHRS().getAngle());

    SmartDashboard.putBoolean("12btn", RobotContainer.getJoy().getRawButton(12));
    SmartDashboard.putBoolean("5btn", RobotContainer.getJoy().getRawButton(5));

    SmartDashboard.putNumber("dial", RobotContainer.getJoy().getRawAxis(3));
    SmartDashboard.putNumber("dial_output", RobotContainer.turn_deg);
    SmartDashboard.putNumber("dial_output", turn_rbt_deg);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() 
  {
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() 
  {
    RobotContainer.getAHRS().reset();
    DriveTrain.rightSpeed = 0;
    DriveTrain.rightSpeed = 0;

    m_autonomousCommand = RobotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    

    RobotContainer.getAHRS().reset();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}