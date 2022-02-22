/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class VisionTurn extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  double bias = 0;
  public static boolean isRunning;
  public static int counter;
  public static boolean isCW;
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionTurn(double bias, boolean isCW)
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;
    this.isCW = isCW;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    isRunning = true;
    counter = 0;
  }

  @Override
  public void execute()
  {


    if(!Robot.hasTarget2 && isCW){
      driveTrain.getRight().set(0.4);
      driveTrain.getLeft().set(-0.4);     
    } else if (!Robot.hasTarget2 && !isCW) {
      driveTrain.getRight().set(-0.4);
      driveTrain.getLeft().set(0.4);    
    } else {
      if(Robot.yaw2 >= 20){
        driveTrain.getRight().set(0.25);
        driveTrain.getLeft().set(-0.25);      
      } else if (Robot.yaw2 >= 6) {
        driveTrain.getRight().set(0.15);
        driveTrain.getLeft().set(-0.15); 
      } else if(Robot.yaw2 > 1){
        driveTrain.getRight().set(0.12);
        driveTrain.getLeft().set(-0.12);      
      } else if(Robot.yaw2 <= -20){
        driveTrain.getRight().set(-0.25);
        driveTrain.getLeft().set(0.25);      
      } else if (Robot.yaw2 <= -6) {
        driveTrain.getRight().set(-0.15);
        driveTrain.getLeft().set(0.15);    
      } else if(Robot.yaw2 < -1){
        driveTrain.getRight().set(-0.12);
        driveTrain.getLeft().set(0.12);

      } 
    }

    
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
    isRunning = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(Robot.yaw2 > -2 && Robot.yaw2 < 2 && Robot.hasTarget2) 
    {
      counter++;
      if(counter > 12){
        return true;
      }
    }


  return false;
  }

}