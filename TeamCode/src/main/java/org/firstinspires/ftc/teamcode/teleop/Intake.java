package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor intake;

    public void init(HardwareMap hwmap){
        intake = hwmap.get(DcMotor.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    public void intakeSpeed(double speed) {
        intake.setPower(speed);
    }


}
