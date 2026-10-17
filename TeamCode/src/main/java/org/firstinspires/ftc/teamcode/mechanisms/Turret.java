package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Turret {
    public DcMotor turretMotor;

    public void init(HardwareMap hwmap){
        turretMotor = hwmap.get(DcMotor.class,"turret");
        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void turretMotorStop(){
        turretMotor.setPower(Constants.turretStop);

    }
    public void turretMotorMax(){
        turretMotor.setPower(Constants.turretMax);
    }
    public void turretMotorReverse(){
        turretMotor.setPower(Constants.turretRev);

    }


}
