package frc.robot.commands.auto;

import javax.crypto.spec.DHGenParameterSpec;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TurnDegreesBetter extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double degrees;
    private double degreesf;
    private double cdegrees;
    private double err;
    private boolean isCW;
    private double speed;

    public TurnDegreesBetter(double degreesf, boolean isCW) {
        addRequirements(RobotContainer.getDriveTrain());
        this.degreesf = degreesf;
        this.isCW = isCW;
    }

    @Override
    public void initialize()
    {
        degrees = Robot.turn_rbt_deg;
        RobotContainer.getAHRS().reset();

    }
  
    @Override
    public void execute()
    {
        cdegrees = Math.abs(RobotContainer.getAHRS().getAngle());

        err = degrees - cdegrees;
        
        if(Math.abs(err) > 25){
            speed = .5;

        } else {
            speed = .15;
        }

        if (isCW) {
            driveTrain.getLeft().set(-speed);
            driveTrain.getRight().set(speed);
        } else if (!isCW) {
            driveTrain.getLeft().set(speed);
            driveTrain.getRight().set(-speed);
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
        if(Math.abs(RobotContainer.getAHRS().getAngle() % 360) >= (degrees - 2) && Math.abs(RobotContainer.getAHRS().getAngle() % 360) <= (degrees + 2)) 
        {    
 
            return true;
            
        }

  
      return false;
    }
}
