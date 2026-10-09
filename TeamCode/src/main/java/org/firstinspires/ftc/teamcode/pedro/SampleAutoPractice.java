package org.firstinspires.ftc.teamcode.pedro;

import static com.pedropathing.api.Paths.*;//
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class SampleAutoPractice {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startingPoint = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(135.2888, 42.4041, 180);

    public Path path1() {
        return Paths.line(startingPoint, path1).linear(startingPoint, path1);
    }
}