package frc.robot.commands.auto;

import javax.crypto.spec.DHGenParameterSpec;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TurnDegrees extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double err;
    private double err_ttl;
    private double errBasedSpd;
    private double degrees;
    private double post_degrees;
    private double cdegrees;
    private double degreesf;
    private double speed;
    private int count;

    public TurnDegrees(double degreesf) {
        addRequirements(RobotContainer.getDriveTrain());
        this.degreesf = degreesf;
    }

    @Override
    public void initialize()
    {
        degrees = Robot.turn_rbt_deg;

        RobotContainer.getAHRS().reset();
        if (degrees > 180) {
            post_degrees = degrees - 180;
        } else {
            post_degrees = degrees;
        }
    }
  
    @Override
    public void execute()
    {
        cdegrees = Math.abs(RobotContainer.getAHRS().getAngle());



        err = post_degrees - cdegrees;
        err_ttl = degrees - cdegrees;
        
        if(Math.abs(err) > 25){
            speed = .5;

        } else {
            speed = .15;
        }

        if(degrees > 180){
            speed *= -1;
        }

        driveTrain.getLeft().set(-speed);
        driveTrain.getRight().set(speed);


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
        if(Math.abs(RobotContainer.getAHRS().getAngle() % 360) >= (post_degrees - 2) && Math.abs(RobotContainer.getAHRS().getAngle() % 360) <= (post_degrees + 2)) 
        {    
 
            return true;
            
        }

  
      return false;
    }
}