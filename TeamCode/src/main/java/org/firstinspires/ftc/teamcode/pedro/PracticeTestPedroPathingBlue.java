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

public class PracticeTestPedroPathingBlue {
//    private Follower follower;
//    private Timer pathTimer, opModeTimer;
//    Shooter shooter = new Shooter();
//    Nudge nudge = new Nudge();
//    Intake intake = new Intake();
//
//    public enum PathState{
//        IDLE,
//
//        DRIVE_AWAY,
//        SHOOT_PRELOAD
//
//    }
//    PathState pathState;
//    private final Pose startPose = new Pose(82.3723745428141,131.86958100258175,Math.toRadians(270));
//    private final Pose shootingPose = new Pose(82.3723745428141,131.86958100258175,Math.toRadians(270));
//    private final Pose endPose = new Pose(138.42,40.78,Math.toRadians(270));
//    private Path driveAwayPath;
//
//
//    public void statePathUpdate(){
//        switch(pathState){
//            case SHOOT_PRELOAD:
//                shooter.spinUp();
//
//                if (pathTimer.seconds() < 1) {
//                    // Wait 1 second for shooter to spin up
//                }
//                else if (pathTimer.seconds() < 4) {
//                    nudge.NudgeMotorMax();
//                    intake.IntakeMotorMax();
//                }
//                else {
//                    shooter.stop();
//                    nudge.NudgeMotorStop();
//                    intake.IntakeMotorStop();
//
//                    follower.follow(driveAwayPath);
//                    setPathState(PracticeTestPedroPathingBlue.PathState.DRIVE_AWAY);
//                }
//                break;
//            case DRIVE_AWAY:
//                if (!follower.isBusy()) {
//                    setPathState(PathState.IDLE);
//
//                }
//                break;
//            case IDLE:
//
//                telemetry.addLine("Autonomous Complete");
//                break;
//
//
//
//
//            default:
//                telemetry.addLine("No State Command");
//
//        }
//
//
//    }
//    public void setPathState(PathState newState){
//        pathState = newState;
//        pathTimer.reset();
//    }
//    public void init(){
//        pathTimer = new Timer();
//        shooter.init(hardwareMap);
//        nudge.init(hardwareMap);
//        intake.init(hardwareMap);
//        follower = Constants.create(hardwareMap);
//        follower.setPose(startPose);
//
//        driveAwayPath = Paths.line(shootingPose, endPose)
//                .linear(shootingPose, endPose);
//
//
//
//
//
//
//
//    }
//    public void loop(){
//        follower.update();
//        statePathUpdate();
//
//        telemetry.addData("Path State", pathState);
//        telemetry.addData("Path Timer", pathTimer.seconds());
//        telemetry.update();
//
//    }
//    @Override
//    public void start() {
//        setPathState(PathState.SHOOT_PRELOAD);
//    }
}
