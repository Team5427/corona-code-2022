package frc.robot.commands.auto;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class VisionBall extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    private double bias = 0;
    private double slowSpeed;
    private double fastSpeed;
    private double err;
    private double setSpeedLeft;
    private double setSpeedRight;
    private boolean locked;

    private double timer = 0;

    private PhotonCamera cam;
    private PhotonTrackedTarget target;
    private boolean hasTarget;
    private double pitch;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public VisionBall(double bias, double slowSpeed, double fastSpeed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
      this.slowSpeed = slowSpeed;
      this.fastSpeed = fastSpeed;
    }

    @Override
    public void initialize() {
        locked = false;
        cam = new PhotonCamera("photoncam2");
        System.out.println("tracking ball");
    }

    @Override
    public void execute() {

        if(cam.getLatestResult().hasTargets()){
            hasTarget = cam.getLatestResult().hasTargets();
            target = cam.getLatestResult().getBestTarget();
            err = target.getYaw();
            pitch = target.getPitch();
        }

        
        if(!hasTarget){
            driveTrain.getRight().set(slowSpeed);
            driveTrain.getLeft().set(-slowSpeed);
        } else {
            locked = true;
            if (err < -3) {
                setSpeedLeft = -slowSpeed;
                setSpeedRight = -fastSpeed;
            } if (err > 3) {
                setSpeedRight = -slowSpeed;
                setSpeedLeft = -fastSpeed;
            } else if (err <= 3 && err >= -3) {
                setSpeedRight = -fastSpeed;
                setSpeedLeft = -fastSpeed;
            }
            
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }

    }

    @Override
    public boolean isFinished() {
        if (pitch <= -9 && (err >= -3 || err <= 3) ) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
