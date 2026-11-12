package org.firstinspires.ftc.teamcode.mechanisms;


import com.pedropathing.math.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;


@TeleOp
public class RevRadius extends OpMode {
    Shooter shooter = new Shooter();
    Pose startPose = new Pose(144,144);

    Pose targetPose = new Pose(71,23);


    double radius = 20;


    private GoBildaPinpointDriver pinpoint;
    @Override

    public void init(){
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        pinpoint.setOffsets(-3.256146588663417, -2.367825996218704, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpoint.resetPosAndIMU();
        pinpoint.setPosition(new Pose2D(
                DistanceUnit.INCH,
                startPose.x(),
                startPose.y(),
                AngleUnit.RADIANS,
                startPose.heading()
        ));
        shooter.init(hardwareMap);

    }
    @Override
    public void loop(){
        pinpoint.update();

        telemetry.addData("Pinpoint Status", pinpoint.getDeviceStatus());
        telemetry.addData("Encoder X", pinpoint.getEncoderX());
        telemetry.addData("Encoder Y", pinpoint.getEncoderY());


        Pose currentPose = new Pose(pinpoint.getPosX(DistanceUnit.INCH),
        pinpoint.getPosY(DistanceUnit.INCH));
        double distance = currentPose.distance(targetPose);
        if(distance<=radius){
            shooter.spinUp();
        } else {
            shooter.stop();

        }

        telemetry.addData("X", currentPose.x());
        telemetry.addData("Y", currentPose.y());
        telemetry.addData("Distance", distance);
        telemetry.addData("Shooter Active", distance <= radius);
        telemetry.update();


    }
}
