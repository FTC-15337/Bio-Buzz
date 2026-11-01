package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Nudge;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;


@Autonomous

public class PracticeTestPedroPathingRed extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModeTimer;
    Shooter shooter = new Shooter();
    Nudge nudge = new Nudge();
    Intake intake = new Intake();

    public enum PathState{
        IDLE,

        DRIVE_AWAY,
        SHOOT_PRELOAD

    }
    PathState pathState;
    private final Pose startPose = new Pose(56,8,Math.toRadians(90));
    private final Pose shootingPose = new Pose(56,8,Math.toRadians(90));
    private final Pose endPose = new Pose(7.63,98.75,Math.toRadians(180));
    private Path driveAwayPath;


    public void statePathUpdate(){
        switch(pathState){
            case SHOOT_PRELOAD:
                shooter.spinUp();

                if(pathTimer.seconds()>8){
                    nudge.NudgeMotorMax();
                    intake.IntakeMotorMax();

                }
                else if(pathTimer.seconds()>10){
                    shooter.stop();
                    nudge.NudgeMotorStop();
                    intake.IntakeMotorStop();
                    follower.follow(driveAwayPath);
                    setPathState(PathState.DRIVE_AWAY);

                }
                break;
            case DRIVE_AWAY:
                if (!follower.isBusy()) {
                    setPathState(PathState.IDLE);
              
                }
                break;
            case IDLE:

                telemetry.addLine("Autonomous Complete");
                break;




            default:
                telemetry.addLine("No State Command");

        }


    }
    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.reset();
    }
    public void init(){
        pathTimer = new Timer();
        shooter.init(hardwareMap);
        nudge.init(hardwareMap);
        intake.init(hardwareMap);
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);

        driveAwayPath = Paths.line(shootingPose, endPose)
                .linear(shootingPose, endPose);







    }
    public void loop(){
        follower.update();
        statePathUpdate();

        telemetry.addData("Path State", pathState);
        telemetry.addData("Path Timer", pathTimer.seconds());
        telemetry.update();

    }
    @Override
    public void start() {
        setPathState(PathState.SHOOT_PRELOAD);
    }
}
