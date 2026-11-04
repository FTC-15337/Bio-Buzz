package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class NewAutoAlign extends OpMode{
  private GoBildaPinpointDriver pinpoint;

  @Override
  public void init() {
      pinpoint.setEncoderDirections(Constants.localizerConfig.xPodDirection.get(), Constants.localizerConfig.yPodDirection.get());
      pinpoint.setOffsets(Constants.localizerConfig.xPodOffset.get(), Constants.localizerConfig.yPodOffset.get(), Constants.localizerConfig.offsetUnits.get());
      pinpoint.resetPosAndIMU();
  }

  @Override
  public void loop() {
      pinpoint.update();

      double x = pinpoint.getPosX(DistanceUnit.INCH);
      double y = pinpoint.getPosY(DistanceUnit.INCH);
      double heading = pinpoint.getHeading(AngleUnit.DEGREES);

    telemetry.addData("X Position (mm)", x);
    telemetry.addData("Y Position (mm)", y);
    telemetry.addData("Heading (Radians)", heading);
    telemetry.addData("Heading (Degrees)", Math.toDegrees(heading));
  }

}
