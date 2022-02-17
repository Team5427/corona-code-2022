package frc.robot.commands.auto;

import javax.crypto.spec.DHGenParameterSpec;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TurningIsCool extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double setPoint;
    private boolean isAuto;
    private double startAngle;
    private boolean reset = false;

    public TurningIsCool(double setPoint, boolean isAuto) {
        addRequirements(RobotContainer.getDriveTrain());

        this.setPoint = setPoint;
        this.isAuto = isAuto;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getAHRS().reset();
        reset = true;
        if (isAuto) {
            setPoint = setPoint;
        } else if (!isAuto) {
            setPoint = Robot.turn_rbt_deg;
        }
    }
  
    @Override
    public void execute()
    {
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));
        double speed;

        if(Math.abs(angle - setPoint) < 20) {
            speed = .15;
        } 
        else{
            speed = .3;
        }

        if(setPoint > 180){
            speed *= -1;
        }

        RobotContainer.getDriveTrain().getRight().set(speed);

        RobotContainer.getDriveTrain().getLeft().set(-speed);

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
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));
        if(reset && ((setPoint > 180) && angle <= setPoint && angle != 0) || ((setPoint < 180) && angle >= setPoint)){
            reset = false;
            return true;
        }

        return false;
    }
}