package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase
{
    private MotorControllerGroup left, right;

    public static double arcadeSpeed = 0;

    //ramping up 
    public static double rightSpeed, leftSpeed = 0;

    //ramping down
    private DifferentialDrive driveBase;

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
    }

    public MotorControllerGroup getLeft()
    {
        return left;
    }

    public MotorControllerGroup getRight()
    {
        return right;
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        driveBase.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(Joystick joy)
    {
        driveBase.arcadeDrive(joy.getY(), -joy.getZ() * 0.65);
    }

    public DifferentialDrive getDriveBase()
    {
        return driveBase;
    }

    @Override
    public void periodic()
    {}
    
}