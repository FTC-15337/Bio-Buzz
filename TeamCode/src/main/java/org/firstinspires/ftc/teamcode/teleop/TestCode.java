package org.firstinspires.ftc.teamcode.teleop;


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
    public void init(){
//        turret.init(hardwareMap);
        intake.init(hardwareMap);
        drive.init(hardwareMap);
        nudge.init(hardwareMap);
        shooter.init(hardwareMap);
        flywheel.init();



    }
    public void loop(){
        if (gamepad1.x){
            intake.IntakeMotorMax();

        } else if (gamepad1.y) {
            intake.IntakeMotorReverse();
        } else {
            intake.IntakeMotorStop();
        }
        if (gamepad1.left_bumper){
            shooter.ShooterMotorMax();
        } else if (gamepad1.right_bumper) {
            shooter.ShooterMotorReverse();
        }else {
            shooter.ShooterMotorStop();
        }

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

        if (gamepad1.b){
            nudge.NudgeMotorMax();

        }else if(gamepad1.a){
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
