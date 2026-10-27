package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Nudge;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;

@TeleOp
public class TestCode extends OpMode {

    Turret turret = new Turret();
    Intake intake = new Intake();
    Drive drive = new Drive();
    Nudge nudge = new Nudge();
    public void init(){
        turret.init(hardwareMap);
        intake.init(hardwareMap);
        drive.init(hardwareMap);
        nudge.init(hardwareMap);

        //speeds up the code execution by reading all hardware maps at once

    }
    public void loop(){
        if (gamepad1.x){
            intake.IntakeMotorMax();
        }else {
            intake.IntakeMotorStop();
        }

        if (gamepad1.b){
            nudge.move(0.5);
        }else {
            nudge.move(0);
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
