package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.util.ElapsedTime;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.FlywheelTunerTutorial;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Nudge;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;

@TeleOp
public class TestCode extends OpMode {

    Turret turret = new Turret();
    Intake intake = new Intake();
    Drive drive = new Drive();
    Nudge nudge = new Nudge();
    FlywheelTunerTutorial flywheel = new FlywheelTunerTutorial();
    Shooter shooter = new Shooter();
    ElapsedTime nudgeTimer = new ElapsedTime();
    boolean nudging = false;
    boolean previousB = false;
    double nudgeDuration = 0.20;
    public void init(){
//        turret.init(hardwareMap);
        intake.init(hardwareMap);
        drive.init(hardwareMap);
        nudge.init(hardwareMap);
        shooter.init(hardwareMap);





    }
    double Target = 1300;
    boolean previousAState = false;
    public void loop(){
        if (gamepad1.a && !previousAState) {
            Target += 20;
        }


        previousAState = gamepad1.a;

        telemetry.addData("Variable Value", Target);
        telemetry.update();


        if (gamepad2.left_trigger_pressed){
            intake.IntakeMotorMax();

        } else if (gamepad2.right_trigger_pressed) {
            intake.IntakeMotorReverse();
        } else {
            intake.IntakeMotorStop();
        }
        if (gamepad2.right_bumper) {
            shooter.spinUp(Target);
        }else if(gamepad2.left_bumper){
            shooter.reverse();

        } else {
            shooter.stop();
        }

        telemetry.addData("Shooter speed", shooter.getVelocity());
        telemetry.addData("Ready", shooter.isReady());

//        if (gamepad1.right_trigger_pressed){
//            shooter.ShooterMotorMax();
//            intake.IntakeMotorMax();
//           if (gamepad1.b){
//                nudge.move(1);
//                shooter.shoot(0.5);
//                intake.IntakeMotorMax();
//            }
//        }else {
//            shooter.shoot(0);
//            intake.IntakeMotorStop();
//            nudge.move(0);
//        }

        boolean currentB = gamepad2.b;

        if (gamepad2.y) {
            nudging = false;
            nudge.NudgeMotorReverse();
        } else {
            if (currentB && !previousB && !nudging) {
                nudging = true;
                nudgeTimer.reset();
            }

            if (nudging && nudgeTimer.seconds() < nudgeDuration) {
                nudge.NudgeMotorMax();
            } else {
                nudging = false;
                nudge.NudgeMotorStop();
            }
        }

        previousB = currentB;
        double forward, rotate, strafe;
        if (gamepad1.a){
            drive.Reset();
        }
        forward = -gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        drive.driveFieldRelative(forward,strafe,rotate);

    }
}
