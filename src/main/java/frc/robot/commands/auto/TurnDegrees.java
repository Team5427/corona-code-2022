package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TurnDegrees extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double err;
    private double errBasedSpd;
    private double degrees;

    public TurnDegrees(double degrees) {
        addRequirements(RobotContainer.getDriveTrain());
        this.degrees = degrees;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getAHRS().reset();
    }
  
    @Override
    public void execute()
    {
        err = RobotContainer.getAHRS().getAngle();
        if (Math.signum(degrees) == -1.0 || Math.signum(degrees) == 0.0) 
        {
            errBasedSpd = (degrees - err)/(-0.5 * degrees);
            driveTrain.getRight().set(-errBasedSpd);
            driveTrain.getLeft().set(errBasedSpd);
        } else if (Math.signum(degrees) == 1.0) 
        {
            errBasedSpd = (degrees - err)/(0.5 * degrees);
            driveTrain.getRight().set(errBasedSpd);
            driveTrain.getLeft().set(-errBasedSpd);
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
        if(RobotContainer.getAHRS().getRate() > -10 && RobotContainer.getAHRS().getRate() < 10) 
        {
            return true;
        }

  
      return false;
    }
}
