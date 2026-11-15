package org.firstinspires.ftc.teamcode.pedro;


import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.PoseStorage;


public class goToPos {
    public Follower follower;
    Pose target = new Pose(59.16, 110.7, Math.toRadians(0));
    boolean holding = false;

    public void init(HardwareMap hardwareMap) {
        follower = Constants.create(hardwareMap);
        follower.setPose(new Pose(
                PoseStorage.pose.getX(DistanceUnit.INCH),
                PoseStorage.pose.getY(DistanceUnit.INCH),
                PoseStorage.pose.getHeading(AngleUnit.RADIANS)));
    }

    public void update(boolean b, double forward, double strafe, double turn) {
        if (b) {
            if (!holding) {
                follower.hold(target);
                holding = true;
            }
        } else {
            holding = false;
            follower.manual(forward, strafe, turn);
        }
        follower.update();
    }
}