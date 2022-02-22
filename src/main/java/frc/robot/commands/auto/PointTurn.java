/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PointTurn extends PIDCommand {
  /**
   * Creates a new PointTurn.
   */
  private double angle;
  private double angleTolerance = 2;
  public PointTurn(double setAngle) {
    
    super(
        // The controller that the command will use
        //0.0080111---> original P value
        new PIDController(0.001,0,0),
        // This should return the measurement
        () -> RobotContainer.getAHRS().getYaw(),
        // This should return the setpoint (can also be a constant)
        () -> setAngle,
        // This uses the output
        output -> 
        {
          // Use the output here
          RobotContainer.getDriveTrain().tankDrive(0.3, -0.3);
          System.out.println(output);
        });
        this.angle = setAngle;
        
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getDriveTrain());
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void initialize(){
    RobotContainer.getAHRS().reset();
  }

  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double trackError = angle - RobotContainer.getAHRS().getYaw();
    boolean isFinished = Math.abs(trackError) < angleTolerance;
    return isFinished;
  }

  @Override
  public void end (boolean interrupted){
    RobotContainer.getDriveTrain().stop();
    //System.out.println(RobotContainer.getEncLeft().getDistance());
    System.out.println(RobotContainer.getAHRS().getYaw());

  }

   
 
}
