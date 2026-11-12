package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class Forward extends OpMode {
    Drive drive = new Drive();
    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.drive(0.3,0,0);
    }
}
