/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class VisionTurn extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  double bias = 0;
  private boolean hasTarget;
  private double err;
  public static boolean isRunning;
  public static int counter;
  public static boolean isCW;
  private PhotonCamera cam;
  private PhotonTrackedTarget target;
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
    System.out.println("tracking target");
    cam = new PhotonCamera("photoncam");
    isRunning = true;
    counter = 0;
    hasTarget = false;
  }

  @Override
  public void execute()
  {

    if(cam.getLatestResult().hasTargets()){
      hasTarget = cam.getLatestResult().hasTargets();
      target = cam.getLatestResult().getBestTarget();
      err = target.getYaw();
    }

    if(!hasTarget && isCW){
      driveTrain.getRight().set(0.4);
      driveTrain.getLeft().set(-0.4);     
    } else if (!hasTarget && !isCW) {
      driveTrain.getRight().set(-0.4);
      driveTrain.getLeft().set(0.4);    
    } else {
      if(err >= 20){
        driveTrain.getRight().set(0.25);
        driveTrain.getLeft().set(-0.25);
      } else if (err >= 6) {
        driveTrain.getRight().set(0.2);
        driveTrain.getLeft().set(-0.2);
      } else if (err >= 4) {
        driveTrain.getRight().set(0.15);
        driveTrain.getLeft().set(-0.15); 
      } else if(err > 1){
        driveTrain.getRight().set(0.1);
        driveTrain.getLeft().set(-0.1);      
      } else if(err <= -20){
        driveTrain.getRight().set(-0.25);
        driveTrain.getLeft().set(0.25);
      } else if (err <= -6) {
        driveTrain.getRight().set(-0.2);
        driveTrain.getLeft().set(0.2);
      } else if (err <= -4) {
        driveTrain.getRight().set(-0.15);
        driveTrain.getLeft().set(0.15);    
      } else if(err < -1){
        driveTrain.getRight().set(-0.1);
        driveTrain.getLeft().set(0.1);

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
    if(err > -2 && err < 2 && hasTarget) 
    {
      counter++;
      if(counter > 12){
        return true;
      }
    }


  return false;
  }

}