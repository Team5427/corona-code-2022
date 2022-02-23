package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.VisionTurn;

public class RapidReactAuton extends SequentialCommandGroup{
    public RapidReactAuton(){
        addCommands(new VisionBall(0, 0.2, 0.3), new ForwardTimer(0.5, 0.3), new VisionTurn(0, true), new Wait(1.5), new PointTurn(220, true, 0.15, 0.5, 30), new Wait(1.5), new ForwardTimer(2, 0.4), new VisionBall(0, 0.3, 0.4), new VisionTurn(0, true));
    
}
}
