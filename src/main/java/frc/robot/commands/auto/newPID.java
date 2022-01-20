package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.CAN;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class newPID extends CommandBase {
    
    private double kP, kI, kD;
    private double setPoint;
    private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;

    public newPID(  double kP, double  kI, double kD ) {
          this.kP = kP;
          this.kI = kI;
          this.kD = kD;
          m_pidController = RobotContainer.getCanSparkMax().getPIDController();
          m_encoder =   RobotContainer.getCanSparkMax().getEncoder();        
    }
    public void setValues() {m_pidController.setP(kP);
      m_pidController.setI(kI);
      m_pidController.setD(kD);}
      public double getSetPoint() {
        setPoint = RobotContainer.getJoy().getY() *3600;
        return setPoint;

      }

     
    


    
}
