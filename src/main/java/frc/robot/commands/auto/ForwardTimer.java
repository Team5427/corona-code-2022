package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class ForwardTimer extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private Timer timer = new Timer();
    private double ctimer;
    private double speed;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public ForwardTimer(double ctimer, double speed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
      this.speed = speed;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        driveTrain.getLeft().set(-speed);
        driveTrain.getRight().set(-speed);
    }

    @Override
    public boolean isFinished() {
        if (timer.get() >= ctimer) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
