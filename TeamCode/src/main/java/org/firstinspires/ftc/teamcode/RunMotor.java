package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp

public class RunMotor {

    DcMotor rightFront;


    public void init(HardwareMap hwmap){
        rightFront = hwmap.get(DcMotor.class,"rightFront");
        if(gamepad1.a){
            rightFront.setPower(1);

        }
        else{
            rightFront.setPower(0);
        }


    }
    public void loop(){

    }


}
