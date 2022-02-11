/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.VisionTurn;
import frc.robot.commands.auto.AethiaLeftThreeCells;
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

  
  public static double pitch;
  public static double yaw;
  public static double skew;
  public static double area;
  public static double PixelX;
  public static double PixelY;
  public static boolean hasTarget;

  NetworkTable table2;

  
  public static double pitch2;
  public static double yaw2;
  public static double skew2;
  public static double area2;
  public static double PixelX2;
  public static double PixelY2;
  public static boolean hasTarget2;
  double default_all = 0.0;

  private RobotContainer m_robotContainer;
  
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
    
    NetworkTableInstance PIInstance = NetworkTableInstance.create();
    PIInstance.setServer("ballvision");
    PIInstance.startClient();
    table = PIInstance.getTable("photonvision").getSubTable("photoncam2");

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


    SmartDashboard.putBoolean("Intake Covered", RobotContainer.getTransport().getIntakeCovered());
    SmartDashboard.putBoolean("Transport covered", RobotContainer.getTransport().getTransportCovered());
    SmartDashboard.putBoolean("Pulley Covered", RobotContainer.getPulley().getPulleyCovered());
    SmartDashboard.putBoolean("HasTarget", hasTarget);
    SmartDashboard.putBoolean("Running", VisionTurn.isRunning);

    SmartDashboard.putNumber("Yaw", RobotContainer.getAHRS().getYaw());
    SmartDashboard.putNumber("Left", RobotContainer.getElevator().getLeftEnc().getDistance());
    SmartDashboard.putNumber("Right", RobotContainer.getElevator().getRightEnc().getDistance());
    SmartDashboard.putNumber("Shooter Top Enc Rate", RobotContainer.getShooter().getTopEnc().getRate()*(60.0/1024.0));
    SmartDashboard.putNumber("Shooter Bottom Enc Rate", RobotContainer.getShooter().getBottomEnc().getRate()*(60.0/1024.0));
    SmartDashboard.putNumber("drive train yes yes yes", RobotContainer.getJoy().getY());
    SmartDashboard.putNumber("drive train yes yes yes yes", RobotContainer.getJoy().getAxisCount());

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