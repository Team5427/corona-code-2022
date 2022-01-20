package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AdjustSetPoint extends CommandBase {
    private static double set_setPoint;

    @Override
    public void execute() {
        if (Math.abs(RobotContainer.getJoy().getY()) >= 0.2) {
            set_setPoint = set_setPoint + (RobotContainer.getJoy().getY() * 12);
            
        }
    }
}
