package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class BonkAuto extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private Timer timer = new Timer();
    private double ctimer;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public BonkAuto(double ctimer)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
    }

    @Override
    public void initialize() {
        System.out.println("bonk happened");
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        driveTrain.getLeft().set(-0.3);
        driveTrain.getRight().set(-0.3);
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
