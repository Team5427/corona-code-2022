package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    MotorController arm_motor;
    Encoder arm_enc;
    private PIDController shooter_btm_pid;
    private SimpleMotorFeedforward shooter_ff;
    private double ks;
    private double kv;
    private double ka;

    public Arm(int arm_motor_port, int enc_port_a, int enc_port_b){
        arm_motor = new WPI_VictorSPX(arm_motor_port);
        arm_motor.setInverted(true);
        arm_enc = new Encoder(enc_port_a, enc_port_b);
        //0.00062938
        shooter_ff = new SimpleMotorFeedforward(ks, kv, ka);
        shooter_btm_pid = new PIDController(0.000025, 0.0000001005, 0.00000000);
        arm_enc.setDistancePerPulse(1/1024);
        //returns value in rotations

    }

    public void moveArm(double speed){
        arm_motor.set(shooter_btm_pid.calculate(arm_enc.getRate(), 20));
        // arm_motor.set(.25);
    }

    public double getShooterCalculate(){
        return shooter_btm_pid.calculate(arm_enc.getRate(), 20);
    }

    public Encoder getEnc(){
        return arm_enc;
    }

    public void moveArmFeedforward(double setPointRPS){
        arm_motor.setVoltage(shooter_btm_pid.calculate(arm_enc.getRate(), 20) 
        + shooter_ff.calculate(setPointRPS));
    }

    public void stopArm(){
        arm_motor.stopMotor();
    }
}
