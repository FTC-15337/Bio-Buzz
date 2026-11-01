package org.firstinspires.ftc.teamcode.mechanisms;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constantsfile;

public class Shooter {
    private DcMotor shooter;
    public void init(HardwareMap hwmap){
        shooter = hwmap.get(DcMotor.class,"shooter");
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void ShooterMotorStop(){
        shooter.setPower(Constantsfile.shooterStop);
    }
    public void ShooterMotorMax(){
        shooter.setPower(Constantsfile.shooterMax);
    }
    public void ShooterMotorReverse(){
        shooter.setPower(Constantsfile.shooterRev);
    }
}
