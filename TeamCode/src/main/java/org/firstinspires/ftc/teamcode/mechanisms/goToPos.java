package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class goToPos {
    private GoBildaPinpointDriver pinpoint;
    Drive drive = new Drive();

    public void init(HardwareMap hwmap){
        pinpoint = hwmap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(-3.256146588663417, -2.367825996218704, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpoint.setPosition(PoseStorage.pose);
        drive.init(hwmap);
    }
    public void run(double rotate){
        double shootY = 111.13;
        double shootX = 58.04;
        double robotX = pinpoint.getPosX(DistanceUnit.INCH);
        double robotY = pinpoint.getPosY(DistanceUnit.INCH);
        double tol = 2;
        double KP  =0.05;

        double run = shootY-robotY;
        double rise = shootX-robotX;

        if (Math.abs(run)>tol) {
            run *= KP;

        }
        if (Math.abs(rise)>tol){
            rise *=KP;
        }

        drive.driveFieldRelative(rise, run, rotate);
    }
}
