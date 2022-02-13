package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class moveStraight extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;
    double slowSpeed;
    double fastSpeed;
    double err;
    double setSpeedLeft;
    double setSpeedRight;
    boolean locked;

    double timer = 0;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public moveStraight(double bias, double slowSpeed, double fastSpeed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
      this.slowSpeed = slowSpeed;
      this.fastSpeed = fastSpeed;
    }

    @Override
    public void initialize() {
        locked = false;
    }

    @Override
    public void execute() {
        err = Robot.yaw;
        
        if(!Robot.hasTarget){
            driveTrain.getRight().set(slowSpeed);
            driveTrain.getLeft().set(-slowSpeed);
        } else {

            locked = true;
            if (err < -3) {
                setSpeedLeft = -slowSpeed;
                setSpeedRight = -fastSpeed;
            } if (err > 3) {
                setSpeedRight = -slowSpeed;
                setSpeedLeft = -fastSpeed;
            }
            
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }
    }

    @Override
    public boolean isFinished() {
        if (Robot.pitch <= 4 && (Robot.yaw >= -3 || Robot.yaw <= 3) ) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
