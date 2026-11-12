package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp
public class autoAlign  {
    private GoBildaPinpointDriver pinpoint;
    Drive drive = new Drive();

   public void init(HardwareMap hwmap) {
       pinpoint = hwmap.get(GoBildaPinpointDriver.class, "pinpoint");
       pinpoint.setOffsets(-3.256146588663417, -2.367825996218704, DistanceUnit.INCH);
       pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
       pinpoint.setEncoderDirections(
               GoBildaPinpointDriver.EncoderDirection.FORWARD,
               GoBildaPinpointDriver.EncoderDirection.REVERSED);
       pinpoint.setPosition(PoseStorage.pose);

       drive.init(hwmap);
   }


   public double run() {
       pinpoint.update();
       double x = pinpoint.getPosX(DistanceUnit.INCH);
       double y = pinpoint.getPosY(DistanceUnit.INCH);
       double hiveX = 57.81;
       double hiveY = 91.71;
       double KP = 1;
       double tol = 3;
       double rotate;

           double target = Math.atan2(hiveY - y, hiveX - x);
           double error = target - pinpoint.getHeading(AngleUnit.RADIANS);

           if (error > Math.PI) error -= 2 * Math.PI;
           if (error < -Math.PI) error += 2 * Math.PI;

           if (Math.abs(error) > Math.toRadians(tol)) {
               rotate = -KP * error;
           } else {
               rotate = 0;
           }
           return rotate;
       }
   }

