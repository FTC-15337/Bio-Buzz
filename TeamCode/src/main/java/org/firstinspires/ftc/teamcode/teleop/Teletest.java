package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "teletest")
public class Teletest extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor fl = hardwareMap.dcMotor.get("frontLeft");
        DcMotor bl = hardwareMap.dcMotor.get("backLeft");
        DcMotor fr = hardwareMap.dcMotor.get("frontRight");
        DcMotor br = hardwareMap.dcMotor.get("backRight");

        // Left side reversed. If the robot drives backward on forward stick,
        // reverse fr and br instead.
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);

        for (DcMotor m : new DcMotor[]{fl, bl, fr, br}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        // MUST match how the Control Hub is physically mounted.
        IMU imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT)));
        imu.resetYaw();

        waitForStart();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // Options (Start on Xbox) re-zeroes heading: face robot away from you first.
            if (gamepad1.options) imu.resetYaw();

            double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = (x * Math.cos(-heading) - y * Math.sin(-heading)) * 1.1;
            double rotY = x * Math.sin(-heading) + y * Math.cos(-heading);

            double d = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

            // Full speed by default; hold right trigger for slow mode.
            double speed = gamepad1.right_trigger > 0.5 ? 0.4 : 1.0;

            fl.setPower((rotY + rotX + rx) / d * speed);
            bl.setPower((rotY - rotX + rx) / d * speed);
            fr.setPower((rotY - rotX - rx) / d * speed);
            br.setPower((rotY + rotX - rx) / d * speed);

            telemetry.addData("Heading (deg)", Math.toDegrees(heading));
            telemetry.update();
        }
    }
}
