package org.firstinspires.ftc.teamcode.mechanisms;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constantsfile;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Shooter {


        private DcMotorEx shooter;
        private double targetVelocity = 1180;

        private double tolerance = 15;
    private VoltageSensor battery;
    private ElapsedTime voltTimer = new ElapsedTime();

        public void init(HardwareMap hwmap) {
            shooter = hwmap.get(DcMotorEx.class, "shooter");
            shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            battery = hwmap.voltageSensor.iterator().next();
            voltTimer = new ElapsedTime();
            double v = battery.getVoltage();
            voltTimer.reset();
            shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,

                    new PIDFCoefficients(20, 0, 0,19 * 12 / v ));
        }

        public void pidf(){
            if (voltTimer.seconds() > 2) {
                double v = battery.getVoltage();
                shooter.setVelocityPIDFCoefficients(20, 0, 0, 19 * 12.0 / v);
                voltTimer.reset();
            }

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


