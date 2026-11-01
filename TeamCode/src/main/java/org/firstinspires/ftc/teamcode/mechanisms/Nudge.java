package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constantsfile;

public class Nudge {
    private DcMotor nudge;

    public void init(HardwareMap hwmap){
        nudge = hwmap.get(DcMotor.class,"nudge");
        nudge.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

   public void NudgeMotorMax(){
        nudge.setPower(Constantsfile.nudgeMax);
   }
   public void NudgeMotorReverse(){
        nudge.setPower(Constantsfile.nudgeRev);
   }
   public void NudgeMotorStop(){
        nudge.setPower(Constantsfile.nudgeStop);
   }
}
