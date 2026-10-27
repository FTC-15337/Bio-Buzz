package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Nudge {
    private DcMotor nudge;

    public void init(HardwareMap hwmap){
        nudge = hwmap.get(DcMotor.class,"nudge");
        nudge.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void move(double speed){
        nudge.setPower(speed);
    }
}
