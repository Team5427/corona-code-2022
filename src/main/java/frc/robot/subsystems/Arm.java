package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    MotorController arm_motor;
    Encoder arm_enc;
    private PIDController shooter_btm_pid;

    public Arm(int arm_motor_port, int enc_port_a, int enc_port_b){
        arm_motor = new WPI_VictorSPX(arm_motor_port);
        arm_motor.setInverted(true);
        arm_enc = new Encoder(enc_port_a, enc_port_b);
        //9.4777E-05
        shooter_btm_pid = new PIDController(0.097052, 0, 0);
        arm_enc.setDistancePerPulse(1);

    }

    public void moveArm(double speed){
        arm_motor.set(shooter_btm_pid.calculate(-arm_enc.getRate()/1024, 40));
    }

    public double getShooterCalculate(){
        return shooter_btm_pid.calculate(-arm_enc.getRate()/1024, 40);
    }

    public Encoder getEnc(){
        return arm_enc;
    }

    public void stopArm(){
        arm_motor.stopMotor();
    }
}
