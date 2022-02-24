package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.VisionTurn;

public class AutonThreeBallsAlpha extends SequentialCommandGroup{
    public AutonThreeBallsAlpha(){
        addCommands(new VisionBall(0, 0.4, 0.5), new VisionTurn(0, true), new Wait(1), new PointTurn(300, true, .15, 0.5, 20), new VisionBall(0, 0.3, 0.4), new VisionTurn(0, true));
    
    }
}
