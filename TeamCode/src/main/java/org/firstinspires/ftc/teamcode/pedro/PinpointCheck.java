package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Pose;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Pinpoint Check")
public class PinpointCheck extends LinearOpMode {
    @Override
    public void runOpMode() {
        Localizer localizer = new PinpointLocalizer(hardwareMap, Constants.localizerConfig);
        waitForStart();
        localizer.setPose(Pose.zero());

        while (opModeIsActive()) {
            localizer.update();
            Pose p = localizer.pose();
            telemetry.addData("X", p.x());
            telemetry.addData("Y", p.y());
            telemetry.addData("Heading (deg)", Math.toDegrees(p.heading()));
            telemetry.update();
        }
    }
}