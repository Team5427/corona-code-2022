package frc.robot.commands.auto;

import javax.crypto.spec.DHGenParameterSpec;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class PivotTurnDegrees extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double err;
    private double err_ttl;
    private double errBasedSpd;
    private double degrees;
    private double post_degrees;
    private double cdegrees;
    private double left_speed;
    private double right_speed;
    private int count;

    public PivotTurnDegrees(double degrees) {
        addRequirements(RobotContainer.getDriveTrain());
        this.degrees = degrees;
    }

    @Override
    public void initialize()
    {
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
            left_speed = .5;
            right_speed = .5;

        } else {
            left_speed = .4;
            right_speed = .4;
        }

        if(degrees > 180){
            left_speed *= 0;
            right_speed *= -1;
        } else if (degrees < 180) {
            left_speed *= 1;
            right_speed *= 0;
        }

        driveTrain.getLeft().set(-left_speed);
        driveTrain.getRight().set(right_speed);


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
