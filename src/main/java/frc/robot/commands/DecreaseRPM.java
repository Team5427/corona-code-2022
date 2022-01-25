package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DecreaseRPM extends CommandBase{
    public int count = 0;
    public DecreaseRPM(){

    }

    @Override
    public void initialize(){
        System.out.println("=============================");
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
        System.out.println("changed " + count);
        count++;
        RobotContainer.getShooter().addToSetPoint(-100);
    }

}
