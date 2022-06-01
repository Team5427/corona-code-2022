package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveArm extends CommandBase{
    private double speed;
    public MoveArm(double speed){
        addRequirements(RobotContainer.getArm());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        RobotContainer.getArm().moveArm(speed);
    }

    @Override 
    public void execute(){
        RobotContainer.getArm().moveArm(speed);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getArm().stopArm();
    }

    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.ARM_UP_BUTTON);
    }
}
