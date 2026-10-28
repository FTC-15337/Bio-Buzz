package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotor shooter;
    public void init(HardwareMap hwmap){
        shooter = hwmap.get(DcMotor.class,"shooter");
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void shoot(double speed){
        shooter.setPower(speed);
    }
}
