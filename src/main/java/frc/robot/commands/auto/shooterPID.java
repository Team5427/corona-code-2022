package frc.robot.commands.auto;

import java.lang.invoke.ConstantBootstraps;

import edu.wpi.first.math.controller.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;

import frc.robot.Constants;
import frc.robot.RobotContainer;


public class shooterPID extends PIDCommand {
    private double time;
    private double commandTime;
    
    
    

    public shooterPID(double time) {
        
        
      super(
        Constants.kP, Constants.kI, Constants.kD


      );

      
        
        //TODO Auto-generated constructor stub
    }

    public static PIDController getShooterPID()
    {
        PIDController shooterPID = new PIDController(Constants.kP, Constants.kI, Constants.kD);
        return shooterPID;
    }

    @Override
    protected double returnPIDInput() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected void usePIDOutput(double output) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}