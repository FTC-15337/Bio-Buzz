package org.firstinspires.ftc.teamcode.teleop;
import org.firstinspires.ftc.teamcode.mechanisms.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestCode")

public class TestCode extends OpMode {

        Turret turret = new Turret();
        Intake intake = new Intake();

    public void init(){
        turret.init(hardwareMap);
        intake.init(hardwareMap);

    }
    public void loop(){
        if(gamepad1.left_bumper){
            turret.turretMotorReverse();

        }
        else if(gamepad1.right_bumper){
            turret.turretMotorMax();
        }
        else{
            turret.turretMotorStop();
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
