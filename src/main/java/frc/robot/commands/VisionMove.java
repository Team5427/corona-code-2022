package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class VisionMove extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  double bias = 0;
  boolean found = false;
  double start = Timer.getFPGATimestamp();
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionMove(double bias)
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    found = false;
  }

  @Override
  public void execute()
  {


    if(!Robot.hasTarget){
      driveTrain.getRight().set(0.2);
      driveTrain.getLeft().set(0.2);
    }
    else{
        if(Robot.pitch < 4){
            driveTrain.getRight().set(0.2);
            driveTrain.getLeft().set(-0.2);
        } else if(Robot.pitch > 10){
            driveTrain.getRight().set(-0.2);
            driveTrain.getLeft().set(0.2);
        }
    }
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(!Robot.hasTarget || (Robot.pitch < 4 || Robot.pitch > 10)){

        return false;
    }
    // if(!found){
    //   start = Timer.getFPGATimestamp();
    //   found = true;

    // }
    // if(Timer.getFPGATimestamp() - start >= 1)
    // return true;

    // return false;
    return true;
  }

}



