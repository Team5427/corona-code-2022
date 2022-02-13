package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RapidReactNoVision extends SequentialCommandGroup{
    public RapidReactNoVision(){
        addCommands(new MoveStraightPID(1.7),new Wait(.5), new TurnDegrees(180));
    }
}
