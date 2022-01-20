package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    private double speed;
    public static double lsetPoint;
    public static double rsetPoint;
    public static double setPointFinal;
    
    public MoveShooterTeleop(double speed)
    {
        addRequirements(RobotContainer.getShooter());
        this.speed = speed;
    }

    @Override
    public void initialize() {
        lsetPoint = 0;
        rsetPoint = 0;
        setPointFinal = 3600;
        //RobotContainer.getShooter().moveShooter(speed);
    }

    @Override
    public void execute() {

        setPointFinal = setPointFinal + (RobotContainer.getJoy().getY() * 12);

        if (RobotContainer.getShooter().getTopEnc().getVelocity() <= setPointFinal && RobotContainer.getJoy().getRawButton(1) && lsetPoint < setPointFinal) {
            lsetPoint = lsetPoint + setPointFinal/75;
        } else if (lsetPoint >= setPointFinal && RobotContainer.getJoy().getRawButton(1)) {
            lsetPoint = setPointFinal;
        } else if (!RobotContainer.getJoy().getRawButton(1)) {
            lsetPoint = 0;
        }
        if (RobotContainer.getShooter().getBottomEnc().getVelocity() <= setPointFinal && RobotContainer.getJoy().getRawButton(1) && rsetPoint < setPointFinal) {
            rsetPoint = rsetPoint + setPointFinal/75;
        } else if (rsetPoint >= setPointFinal && RobotContainer.getJoy().getRawButton(1)) {
            rsetPoint = setPointFinal;
        } else if (!RobotContainer.getJoy().getRawButton(1)) {
            rsetPoint = 0;
        }

        RobotContainer.getShooter().moveShooter(lsetPoint, rsetPoint);
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.SHOOTER_TELEOP);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}