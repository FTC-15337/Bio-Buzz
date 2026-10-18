package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;

@TeleOp
public class TestCode extends OpMode {
    Turret turret = new Turret();
    Intake intake = new Intake();
    public void init(){
        turret.init(hardwareMap);
        intake.init(hardwareMap);

    }
    public void loop(){
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

    }
}
