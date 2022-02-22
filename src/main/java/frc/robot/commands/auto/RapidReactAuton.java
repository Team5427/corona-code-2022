package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.VisionTurn;

public class RapidReactAuton extends SequentialCommandGroup{
    public RapidReactAuton(){
        addCommands(new moveStraight(0, 0.2, 0.3), new VisionTurn(0, true), new Wait(1.5), new TurningIsCool(250, true), new Wait(0.5), new moveStraight(0, 0.3, 0.4), new VisionTurn(0, true), new TurningIsCool(190, true));
        //, new VisionTurn(0, true), new Wait(4))
    }
}
