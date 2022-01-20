package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase
{
    private CANSparkMax shooterMotorTop;
    private CANSparkMax shooterMotorBottom;
    private RelativeEncoder shooterTopEnc;
    private RelativeEncoder shooterBottomEnc;

    private SparkMaxPIDController pid_top_ss;
    private SparkMaxPIDController pid_btm_ss;
    public double kP_Top, kI_Top, kD_Top, kIz_Top, kFF_Top, kMaxOutput_Top, kMinOutput_Top, maxRPM_Top;
    public double kP_Btm, kI_Btm, kD_Btm, kIz_Btm, kFF_Btm, kMaxOutput_Btm, kMinOutput_Btm, maxRPM_Btm;

  
    
    public Shooter(CANSparkMax shooterMotorTop, CANSparkMax shooterMotorBottom, RelativeEncoder top, RelativeEncoder bottom, SparkMaxPIDController pid_top, SparkMaxPIDController pid_btm)
    {
        this.shooterMotorTop = shooterMotorTop;
        this.shooterMotorBottom = shooterMotorBottom;
        shooterTopEnc = top;
        shooterBottomEnc = bottom;
        pid_top_ss = pid_top;
        pid_btm_ss = pid_btm;
    }

    public CANSparkMax getShooterMotorTop()
    {
        return shooterMotorTop;
    }

    public CANSparkMax getShooterMotorBottom()
    {
        return shooterMotorBottom;
    }

    public RelativeEncoder getTopEnc () {
        return shooterTopEnc;
    }

    public RelativeEncoder getBottomEnc () {
        return shooterBottomEnc;
    }

    public void shooterInitTop() {
            // PID coefficients
    kP_Top = 0.00015; 
    kI_Top = 0.0000012;
    kD_Top = 0; 
    kIz_Top = 0; 
    kFF_Top = 0.000015; 
    kMaxOutput_Top = 1; 
    kMinOutput_Top = -1;
    maxRPM_Top = 5700;

    // set PID coefficients
    pid_top_ss.setP(kP_Top);
    pid_top_ss.setI(kI_Top);
    pid_top_ss.setD(kD_Top);
    pid_top_ss.setIZone(kIz_Top);
    pid_top_ss.setFF(kFF_Top);
    pid_top_ss.setOutputRange(kMinOutput_Top, kMaxOutput_Top);
    } 

    public void shooterInitBtm() {
            // PID coefficients
    kP_Btm = 0.00015; 
    kI_Btm = 0.0000012;
    kD_Btm = 0; 
    kIz_Btm = 0; 
    kFF_Btm = 0.000015; 
    kMaxOutput_Btm = 1; 
    kMinOutput_Btm = -1;
    maxRPM_Btm = 5700;

    // set PID coefficients
    pid_btm_ss.setP(kP_Btm);
    pid_btm_ss.setI(kI_Btm);
    pid_btm_ss.setD(kD_Btm);
    pid_btm_ss.setIZone(kIz_Btm);
    pid_btm_ss.setFF(kFF_Btm);
    pid_btm_ss.setOutputRange(kMinOutput_Btm, kMaxOutput_Btm);
    } 

    public void moveShooter(double tsetpoint, double bsetpoint)
    {
        pid_top_ss.setReference(tsetpoint, CANSparkMax.ControlType.kVelocity);
        pid_btm_ss.setReference(bsetpoint, CANSparkMax.ControlType.kVelocity);
    }

    public void stop()
    {
        shooterMotorBottom.set(0);
        shooterMotorTop.set(0);
    }
}