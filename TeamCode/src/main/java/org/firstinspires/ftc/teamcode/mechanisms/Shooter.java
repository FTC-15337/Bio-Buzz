package org.firstinspires.ftc.teamcode.mechanisms;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constantsfile;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class  Shooter {

        private DcMotorEx shooter;


        private double targetVelocity = 800;   // ticks per second (about 4,500 RPM)
        private double tolerance = 50;          // how close counts as "ready"

        public void init(HardwareMap hwmap) {
            shooter = hwmap.get(DcMotorEx.class, "shooter");
            shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        public void spinUp(double s) {
            shooter.setVelocity(s);
        }

        public void stop() {
            shooter.setVelocity(0);
        }
        public void reverse(){
            shooter.setVelocity(-500);
        }

        public boolean isReady() {
            return Math.abs(shooter.getVelocity() - targetVelocity) < tolerance;
        }

        public double getVelocity() {
            return shooter.getVelocity();
        }

        public void setTargetVelocity(double velocity) {
            targetVelocity = velocity;
        }
    }


