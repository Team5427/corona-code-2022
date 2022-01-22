/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.commands.auto.AethiaLeftThreeCells;
//import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.MoveShooterTeleop;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    
    m_robotContainer = new RobotContainer();
    
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

    SmartDashboard.putNumber("left RPM", RobotContainer.getShooter().getBottomEnc().getVelocity());
    SmartDashboard.putNumber("right RPM", RobotContainer.getShooter().getTopEnc().getVelocity());
    SmartDashboard.putNumber("Voltage?", 1/RobotContainer.shooterMotorTop.getBusVoltage());
    SmartDashboard.putNumber("Change RPM", 4560);
    System.out.println(SmartDashboard.getNumber("Change RPM", 4560));
    SmartDashboard.putNumber("Power?", RobotContainer.pdp.getCurrent(12));

    CommandScheduler.getInstance().run();


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
    // RobotContainer.shooterMotorBottom.setInverted(false);

    
    // RobotContainer.shooterMotorTop.setInverted(true);

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
      SmartDashboard.putNumber("Shooter Top Enc RPM", RobotContainer.getShooter().getTopEnc().getVelocity());
      SmartDashboard.putNumber("Shooter Bottom Enc RPM", RobotContainer.getShooter().getBottomEnc().getVelocity());
      SmartDashboard.putNumber("Final_Setpoint", MoveShooterTeleop.setPointFinal);
      SmartDashboard.putNumber("Current_Setpoint_Top", MoveShooterTeleop. lsetPoint);
      SmartDashboard.putNumber("Current_Setpoint_Btm", MoveShooterTeleop. rsetPoint);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}