package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Intake {
    private DcMotor intakeMotorRight1;


    private
    DcMotor intakeMotor;

    public void init(HardwareMap hwmap){
        intakeMotor = hwmap.get(DcMotor.class, "intake");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void IntakeMotorStop(){
        intakeMotor.setPower(Constants.intakeStop);
    }

    public void IntakeMotorMax(){
        intakeMotor.setPower(Constants.intakeMax);
    }

    public void IntakeMotorReverse(){
        intakeMotor.setPower(Constants.intakeRev);
    }





}
