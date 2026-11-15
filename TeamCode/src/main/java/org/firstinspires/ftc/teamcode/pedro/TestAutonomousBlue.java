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
    //tracks what path quasar is on
    private Timer pathTimer;
    // tracks how much time it takes for the path
    Shooter shooter = new Shooter();
    // invoking the shooter
    Nudge nudge = new Nudge();
    // invoking the kick
    Intake intake = new Intake();
    //invoking the intake

    public enum PathState{
        //assigning the states
        IDLE,
        //The IDLE mode means the Follower is not currently sending commands to the drive motors
        GO_FORWARD,
        GO_RIGHT,


        DRIVE_AWAY,
        /* see line 103. we assign the drive_away state to drive away path
        , in which the robot goes from starting(shooting) pose to the ending(loading_zone)pose
         */
        SHOOT_PRELOAD,
        MOVE_TO_SHOOT


    }
    PathState pathState;
    private final Pose startPose = new Pose(82.97136091321244,132.97136091321244,Math.toRadians(270));
    private final Pose shootingPose = new Pose(83.25,110.3,Math.toRadians(270));
    private final Pose endPose = new Pose(127.92,44.78,Math.toRadians(180));
    private final Pose goRightPose = new Pose(119.04,109.91,Math.toRadians(270));
    private Path driveAwayPath;
    private Path goForwardPath;
    private Path goRightPath;
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
                shooter.setTargetVelocity(1050);
                shooter.spinUp();

                if (shooter.isReady() && !feeding) {
                    feeding = true;
                    pathTimer.reset();
                }
                if (feeding && pathTimer.seconds() < 1.8) {
                    nudge.NudgeMotorReverse();
                    intake.IntakeMotorMax();
                }

                if (feeding && pathTimer.seconds() >= 1.8) {
                    nudge.NudgeMotorStop();
                    intake.IntakeMotorStop();
                    shooter.stop();

                    follower.follow(goRightPath);
                    setPathState(PathState.GO_RIGHT);
                }
                telemetry.addData("vel", shooter.getVelocity());
                break;

            case GO_RIGHT:
                if(!follower.isBusy()){
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

        driveAwayPath = Paths.line(goRightPose, endPose)
                .linear(goRightPose, endPose);
        goForwardPath = Paths.line(startPose,shootingPose) .linear(startPose,shootingPose);
        goRightPath = Paths.line(shootingPose,goRightPose) .linear(shootingPose,goRightPose);

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

