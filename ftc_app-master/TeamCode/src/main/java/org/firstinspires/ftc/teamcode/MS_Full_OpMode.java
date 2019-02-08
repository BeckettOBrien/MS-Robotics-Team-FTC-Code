package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MS Full OpMode", group="Iterative Opmode")
public class MS_Full_OpMode extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor raiseSlide;
    private Servo latchServo;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        raiseSlide = hardwareMap.get(DcMotor.class, "raiseSlide");
        latchServo = hardwareMap.get(Servo.class, "latchServo");

        // Set motor directions
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        raiseSlide.setDirection(DcMotor.Direction.FORWARD);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.addLine("Copyright");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        // POV Mode uses left stick to go forward, and right stick to turn.
        double drive = gamepad1.left_stick_y;
        double turn  =  -gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -0.7, 0.7) ;
        rightPower   = Range.clip(drive - turn, -0.7, 0.7) ;

        // Send calculated power to wheels
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        telemetry.addLine(Integer.toString(leftDrive.getCurrentPosition()));

        // Raise linear slide if right bumper is held down
        if (gamepad1.right_bumper) {
            raiseSlide.setPower(1);
        } else {
            raiseSlide.setPower(0);
        }

        // Lower linear slide if B button is pressed down
        if (gamepad1.b) {
            raiseSlide.setPower(-1);
        } else {
            raiseSlide.setPower(0);
        }

        //Unlock Linear Slide if X button is pressed
        if (gamepad1.x) {
            latchServo.setPosition(0.3);
        }

        //lock Linear Slide if Y button is pressed
        if (gamepad1.y) {
            latchServo.setPosition(0);
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }
}
