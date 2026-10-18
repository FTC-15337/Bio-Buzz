package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;

@TeleOp
public class TestCode extends OpMode {
    Turret turret = new Turret();
    Intake intake = new Intake();
    Drive drive = new Drive();
    public void init(){
        turret.init(hardwareMap);
        intake.init(hardwareMap);
        drive.init(hardwareMap);

    }
    public void loop(){
        double forward, rotate, starfe;
        if(gamepad1.left_bumper){
            turret.TurretMotorReverse();
        }
        else if(gamepad1.right_bumper){
            turret.TurretMotorMax();
        }
        else{
            turret.TurretMotorStop();
        }

        if(gamepad1.a){
            intake.IntakeMotorReverse();
        }
        else if(gamepad1.x){
            intake.IntakeMotorMax();
        }
        else{
            intake.IntakeMotorStop();
        }
        forward = -gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        starfe = gamepad1.left_stick_x;
        drive.driveFieldRelative(forward,starfe,rotate);

    }
}
