package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.VisionTurn;

public class AutonTwoBalls extends SequentialCommandGroup{
    public AutonTwoBalls(){
        addCommands(new VisionBall(0, 0.4, 0.5), new VisionTurn(0, true), new Wait(1));
    
    }
}
