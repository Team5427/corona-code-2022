/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;
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
    System.out.println("TargetTrack happened");
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
        driveTrain.getRight().set(0.3);
        driveTrain.getLeft().set(-0.3);      }
      else if(Robot.yaw2 > 1){
        driveTrain.getRight().set(0.15);
        driveTrain.getLeft().set(-0.15);      }
      else if(Robot.yaw2 <= -20){
        driveTrain.getRight().set(-0.3);
        driveTrain.getLeft().set(0.3);      }
      else if(Robot.yaw2 < -1){
        driveTrain.getRight().set(-0.15);
        driveTrain.getLeft().set(0.15);

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
    if(Robot.yaw2 > -3 && Robot.yaw2 < 3 && Robot.hasTarget2) 
    {
      counter++;
      if(counter > 8){
        return true;
      }
    }


  return false;
  }

}