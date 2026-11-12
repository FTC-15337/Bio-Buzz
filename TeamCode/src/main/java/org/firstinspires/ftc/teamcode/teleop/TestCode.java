package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.FlywheelTunerTutorial;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Nudge;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;
import org.firstinspires.ftc.teamcode.mechanisms.autoAlign;
import org.firstinspires.ftc.teamcode.mechanisms.goToPos;

// inside your loop:

@TeleOp
public class TestCode extends OpMode {

    Turret turret = new Turret();
    Intake intake = new Intake();
    Drive drive = new Drive();
    Nudge nudge = new Nudge();
    autoAlign AutoAlign  = new autoAlign();
    FlywheelTunerTutorial flywheel = new FlywheelTunerTutorial();
    goToPos go = new goToPos();
    Shooter shooter = new Shooter();
    public void init(){
        intake.init(hardwareMap);
        drive.init(hardwareMap);
        nudge.init(hardwareMap);
        shooter.init(hardwareMap);
        AutoAlign.init(hardwareMap);
        VoltageSensor battery = hardwareMap.voltageSensor.iterator().next();
        ElapsedTime voltTimer = new ElapsedTime();
        go.init(hardwareMap);

    }
    double target = 1025;

boolean ready1 =false;
    public void loop(){
        boolean pressed = false;
        shooter.pidf();

        shooter.setTargetVelocity(target);

        if (gamepad2.dpad_up && !pressed){
            target += 50;
            pressed = true;
        }else if (gamepad2.dpad_down && !pressed){
            target -= 50;
            pressed = true;
        }
        if (gamepad2.left_trigger_pressed){
            intake.IntakeMotorMax();

        } else if (gamepad2.right_trigger_pressed) {
            intake.IntakeMotorReverse();
        } else {
            intake.IntakeMotorStop();
        }
        if (gamepad2.right_bumper) {
            shooter.spinUp();
        } else {
            shooter.stop();
        }
        if (gamepad1.b) {
            go.run(AutoAlign.run());

        }
        if (gamepad2.x){
            shooter.spinUp();
            if (shooter.isReady()) {
                ready1 = true;
            } if (ready1){
                shooter.spinUp();
                intake.IntakeMotorMax();
                nudge.NudgeMotorReverse();
            }
        } else if (gamepad2.xWasReleased()) {
            ready1 = false;
            shooter.stop();
            intake.IntakeMotorStop();
            nudge.NudgeMotorStop();
        }
        telemetry.addData("vol",shooter.getVelocity());

        telemetry.addData("Shooter speed", shooter.getVelocity());
        telemetry.addData("Ready", shooter.isReady());
        telemetry.addData("target",target);

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

        if (gamepad2.b){
            nudge.NudgeMotorMax();

        }else if(gamepad2.y){
            nudge.NudgeMotorReverse();
        }
        else {
            nudge.NudgeMotorStop();
        }
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
