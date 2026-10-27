package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.SerialNumber;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.mechanisms.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.net.InetAddress;
@TeleOp

public class AutoAlignTestCode extends OpMode {
    private Limelight3A limelight;
    private final Drive drive = new Drive();
    private final  Intake intake = new Intake();

    // pd setup
   double kP = 0.02;
   double error = 0;
   double lastError = 0;
   double goalX = 0;
   double angleTolerance = 0.2;
   double kD = 0.0001;
   double curTime = 0;
   double lastTime = 0;
   // drive setup

    double forward,strafe,rotate;
    double[] stepSizes = {0.01,0.001,0.0001,0.00001};
    int stepIndex = 2;

    public void init(){
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        telemetry.addLine("Initialized");
    }
    public void start(){
        resetRuntime();
        curTime = getRuntime();
        limelight.start();


    }
    public void loop(){
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
       LLResult result = limelight.getLatestResult();

       if(gamepad1.left_trigger > 0.3 ){
           if (result != null && result.isValid()){
               error = goalX - result.getTx();
               if(Math.abs(error)<angleTolerance){
                   rotate = 0;
                   lastError = error;
                   lastTime = getRuntime();
               }
               else{
                   double pTerm = error *kP;
                   curTime = getRuntime();
                   double dT = curTime - lastTime;
                   double dTerm = 0;

                   if (dT > 0.0001) {
                       dTerm = ((error - lastError) / dT) * kD;
                   }
                   rotate = Range.clip(pTerm + dTerm, -0.4,0.4);
                   lastError = error;
                   lastTime = curTime;
               }
           }else{
               lastTime = getRuntime();
               lastError = 0;
           }

       }else{
           lastTime = getRuntime();
           lastError = 0;
       }
       drive.drive(forward,strafe,rotate);

       if (gamepad1.bWasPressed()) {
           stepIndex = (stepIndex+1) % stepSizes.length;
       }
       if (gamepad1.dpadRightWasPressed()){
           kP-= stepSizes[stepIndex];
       }
       if(gamepad1.dpadLeftWasPressed()){
           kP+=stepSizes[stepIndex];
       }
       if(gamepad1.dpadUpWasPressed()){
           kD+=stepSizes[stepIndex];
       }
       if(gamepad1.dpadDownWasPressed()){
           kD-=stepSizes[stepIndex];
       }
       if(result!= null && result.isValid()){
           if (gamepad1.left_trigger>0.3){
               telemetry.addLine("AUTO ALIGN");

           }

           telemetry.addData("error",error);
       }
       else{
           telemetry.addLine("MANUAL ROTATE MODE");
       }
       telemetry.addLine("----------------------");
       telemetry.addData("Tuning P","%.4F (Dpad l/r", kP);
       telemetry.addData("Tuning D","%.4F(dpad u/d)",kD);
       telemetry.addData("Step Size","%.4F(b button)",stepSizes[stepIndex]);
       telemetry.update();


    }

}
