package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Pose;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pedro.Constants;
@TeleOp
public class autoAlign extends OpMode {
    private GoBildaPinpointDriver pinpoint;
    Drive drive = new Drive();

    @Override
    public void init() {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        pinpoint.setOffsets(-3.256146588663417, -2.367825996218704, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpoint.resetPosAndIMU();
        drive.init(hardwareMap);



    }

    @Override
    public void loop() {
        pinpoint.update();
        double x = pinpoint.getPosX(DistanceUnit.INCH);
        double y = pinpoint.getPosY(DistanceUnit.INCH);
        double hiveX = 30;
        double hiveY = 30;
        double KP = 1;
        double tol = 3;
        double rotate = gamepad1.right_stick_x;

        if (gamepad1.b){
            double target = Math.atan2(hiveY-y,hiveX -x);
            double error = target- pinpoint.getHeading(AngleUnit.RADIANS);

            if (error > Math.PI)  error -= 2 * Math.PI;
            if (error < -Math.PI) error += 2 * Math.PI;

            if (Math.abs(error) > Math.toRadians(tol)) {
                rotate = -KP * error;
            } else {
                rotate = 0;
            }
        }
        drive.driveFieldRelative(-gamepad1.left_stick_y,gamepad1.left_stick_x,rotate);
    }
}
