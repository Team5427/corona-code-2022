package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
//import frc.robot.commands.ShootAll;

public class AethiaCenterThreeCells extends SequentialCommandGroup{

    public AethiaCenterThreeCells()
    {
        addCommands();
        //new ShootAll(Constants.TIME_BETWEEN_CELLS, Constants.TIME_AFTER_CELLS,1)
    }
    
}
