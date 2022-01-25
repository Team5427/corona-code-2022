package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class IncreaseRPM extends CommandBase{
    public IncreaseRPM(){

    }

    @Override
    public void execute(){
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().addToSetPoint(100);
    }

}
