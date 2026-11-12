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

public class TestAutonomousBlue extends OpMode {
    private Follower follower;
    // Tracks which path the robot is on.
    private Timer pathTimer;
    // Tracks how much time it takes for the path
    Shooter shooter = new Shooter();
    // invoking the shooter
    Nudge nudge = new Nudge();
    // invoking the kick
    Intake intake = new Intake();
    // invoking the intake

    public enum PathState{
        // assigning the states
        IDLE,
        // The IDLE mode means the Follower is not currently sending commands to the drive motors
        GO_FORWARD,


        DRIVE_AWAY,
        /* see line 103. we assign the drive_away state to drive away path
        , in which the robot goes from starting(shooting) pose to the ending(loading_zone)pose
         */
        SHOOT_PRELOAD,
        MOVE_TO_SHOOT


    }
    PathState pathState;
    private final Pose startPose = new Pose(56,8,Math.toRadians(90));
    private final Pose shootingPose = new Pose(58.09,31.22,Math.toRadians(90));
    private final Pose endPose = new Pose(7.63,98.75,Math.toRadians(180));
    private Path driveAwayPath;
    private Path goForwardPath;
    private boolean feeding = false;


    public void statePathUpdate(){
        switch(pathState){
            case GO_FORWARD:
                if(!follower.isBusy()){
                    shooter.spinUp();

                    follower.follow(goForwardPath);
                    setPathState(PathState.MOVE_TO_SHOOT);
                }
                break;
            case MOVE_TO_SHOOT:
                if (!follower.isBusy()) {
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;



            case SHOOT_PRELOAD:
                shooter.spinUp();

                if (shooter.isReady() && !feeding) {
                    feeding = true;
                    pathTimer.reset();
                }
                if (feeding && pathTimer.seconds() < 2.0) {
                    nudge.NudgeMotorMax();
                    intake.IntakeMotorMax();
                }

                if (feeding && pathTimer.seconds() >= 2.0) {
                    nudge.NudgeMotorStop();
                    intake.IntakeMotorStop();
                    shooter.stop();

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
        goForwardPath = Paths.line(startPose,shootingPose) .linear(startPose,shootingPose);








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
        feeding = false;
        setPathState(PathState.GO_FORWARD);
    }}

