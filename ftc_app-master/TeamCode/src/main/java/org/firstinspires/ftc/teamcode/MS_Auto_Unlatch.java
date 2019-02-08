package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MS Auto Unlatch", group="Linear Opmode")
public class MS_Auto_Unlatch extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor raiseSlide;
    private Servo latchServo;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        raiseSlide = hardwareMap.get(DcMotor.class, "raiseSlide");
        latchServo = hardwareMap.get(Servo.class, "latchServo");

        // Set directions for motors
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        raiseSlide.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Run until code is finished
        if (opModeIsActive()) {
              // Lower linear slide
              raiseSlide.setPower(1);
              sleep(11000);
              raiseSlide.setPower(0);
              // Unlock the latch
              latchServo.setPosition(0.3);
        }
    }
}