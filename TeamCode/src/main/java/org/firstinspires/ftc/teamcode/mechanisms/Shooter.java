package org.firstinspires.ftc.teamcode.mechanisms;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constantsfile;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Shooter {

        private DcMotorEx shooter;
        private double targetVelocity = 1180;

        private double tolerance = 50;

        public void init(HardwareMap hwmap) {
            shooter = hwmap.get(DcMotorEx.class, "shooter");
            shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                    new PIDFCoefficients(15, 0.001, 0, 14.3));
        }

        public void spinUp() {
            shooter.setVelocity(targetVelocity);
        }

        public void stop() {
            shooter.setVelocity(0);
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

        public void reverse() {
            shooter.setVelocity(-400);
        }
    }


