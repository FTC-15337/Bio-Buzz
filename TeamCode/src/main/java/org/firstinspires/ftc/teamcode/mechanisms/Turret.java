package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constantsfile;

public class Turret {

    public DcMotor turretMotor;

    public void init(HardwareMap hwmap){
        turretMotor = hwmap.get(DcMotor.class,"turret");
        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void TurretMotorStop(){
        turretMotor.setPower(Constantsfile.turretStop);
    }
    public void TurretMotorMax(){
        turretMotor.setPower(Constantsfile.turretRev);
    }
    public void TurretMotorReverse(){
        turretMotor.setPower(Constantsfile.turretMax);
    }
}

