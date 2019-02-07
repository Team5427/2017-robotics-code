package org.usfirst.frc.team5427.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Ultrasonic;

import org.usfirst.frc.team5427.robot.OurClasses.*;

//import org.usfirst.frc.team5427.robot.commands.ExampleCommand;
import org.usfirst.frc.team5427.robot.util.Log;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.commands.MoveServo;
import org.usfirst.frc.team5427.robot.commands.IntakeIn;
import org.usfirst.frc.team5427.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team5427.robot.commands.subsystemControl.*;
import org.usfirst.frc.team5427.robot.network.Client;
//import org.usfirst.frc.team5427.robot.network.SteamworkInterpreter;
import org.usfirst.frc.team5427.robot.network.SteamworkInterpreter;
import org.usfirst.frc.team5427.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	public static OI oi;
	// motor for intake
	public static SpeedController motorPWM_Intake;
	// motor for agitator
	public static SpeedController motorPWM_Agitator;
	// motor for flap
	public static SpeedController motorPWM_Flap;
	// Servo for gate
	public static Servo gateServo;
	// Subsytstem for gate
	public static GateServo gateSub;
	// PWM Motors for Drive Train
	/**
	 * Motor utilized in the DriveTrain. It is located in the front left of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	public static SpeedController motorPWM_FrontLeft;
	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the rear left of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	public static SpeedController motorPWM_RearLeft;
	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the front right of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	public static SpeedController motorPWM_FrontRight;
	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the rear right of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	public static SpeedController motorPWM_RearRight;
	/**
	 * SpeedControllers for TODO
	 */
	public static SpeedController motorPWM_Flywheel;
	public static SpeedController motorPWM_Flywheel2;
	/**
	 * Launcher subsystem to shoot balls from the shooting mechanism
	 */
	public static Launcher launcher;
	/**
	 * Rope Climb subsystem launcher for the robot to climb the rope
	 */
	public static RopeClimb ropeClimb;
	/**
	 * Drive train subsystem to drive the robot
	 */
	public static DriveTrain driveTrain;
	/**
	 * Drive command to drive the robot
	 */
	public static Drive drive;
	/**
	 * Command that sets the intake speed of the robot NOTE: Currently unused,
	 * but if we do decide to use it, the command below can be made to reun for
	 * the entire teleop period and.or the entire autonomous period
	 */
	public static IntakeIn si;
	/**
	 * Intake subsystem to intake balls into the robot
	 */
	public static Intake intake;
	/**
	 * Agitator subsystem to spin balls inside the robot
	 */
	public static Agitator agitator;
	public static DigitalInput limitSwitchDoorOpened;
	public static DigitalInput limitSwitchDoorClosed;
	/**
	 * Network interpreter
	 */
	public static SteamworkInterpreter swip;
	Command autonomousCommand;
	// SendableChooser<Command> chooser = new SendableChooser<>();
	/* -------Sensors------- */
	/** DIO ports for the ultrasonic sensor */
	public static DigitalInput digI = new DigitalInput(Config.ULTRASONIC_ECHO_CHANNEL);
	public static DigitalOutput digO = new DigitalOutput(Config.ULTRASONIC_PING_CHANNEL);
	/**
	 * Ultrasonic Range Finder to find the distance between the sensor and
	 * target
	 */
	public static Ultrasonic ultrasonic = new Ultrasonic(digO, digI);
	/** Gyro for autonomous */
	public static ADXRS450_Gyro gyro;
	/**
	 * Camera server
	 */
	// public static CameraServer server;
	// public static RobotCameras roboCams;
	/**
	 * USB Cameras for robot
	 */
	// public static UsbCamera usbCam0, usbCam1;
	/**
	 * IP Camera
	 */
	// public static AxisCamera axisCam;
	public static Client c;
	/** Current camera in use */
	public static int currentCamera = 0;
	// NI USB interface numbers for the cameras
	// int devForCam0=2,devForCam1=3;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		SmartDashboard.putNumber("testval", 1);
		motorPWM_FrontLeft = new SteelTalon(Config.FRONT_LEFT_MOTOR, Config.OFFSET_FRONT_LEFT_MOTOR_BACKWARD,
				Config.OFFSET_FRONT_LEFT_MOTOR_FORWARD, Config.BIAS_FRONT_LEFT_MOTOR_BACKWARD,
				Config.BIAS_FRONT_LEFT_MOTOR_FORWARD);
		motorPWM_RearLeft = new SteelTalon(Config.REAR_LEFT_MOTOR, Config.OFFSET_REAR_LEFT_MOTOR_BACKWARD,
				Config.OFFSET_REAR_LEFT_MOTOR_FORWARD, Config.BIAS_REAR_LEFT_MOTOR_BACKWARD,
				Config.BIAS_REAR_LEFT_MOTOR_FORWARD);
		motorPWM_FrontRight = new SteelTalon(Config.FRONT_RIGHT_MOTOR, Config.OFFSET_FRONT_RIGHT_MOTOR_BACKWARD,
				Config.OFFSET_FRONT_RIGHT_MOTOR_FORWARD, Config.BIAS_FRONT_RIGHT_MOTOR_BACKWARD,
				Config.BIAS_FRONT_RIGHT_MOTOR_FORWARD);
		motorPWM_RearRight = new SteelTalon(Config.REAR_RIGHT_MOTOR, Config.OFFSET_REAR_RIGHT_MOTOR_BACKWARD,
				Config.OFFSET_REAR_RIGHT_MOTOR_FORWARD, Config.BIAS_REAR_RIGHT_MOTOR_BACKWARD,
				Config.BIAS_REAR_RIGHT_MOTOR_FORWARD);
		motorPWM_Flywheel = new SteelTalon(Config.SHOOTER_MOTOR);
		motorPWM_Flywheel2 = new SteelTalon(Config.ROPE_CLIMB_MOTOR);
		motorPWM_Intake = new SteelTalon(Config.INTAKE_MOTOR, 0, 0);
		motorPWM_Agitator = new SteelSpark(Config.AGITATOR_MOTOR, 0, 0);
		motorPWM_Flap = new SteelSpark(Config.FLAP_MOTOR, 0, 0);
		gateServo = new Servo(Config.GATE_SERVO);
		driveTrain = new DriveTrain(motorPWM_FrontLeft, motorPWM_RearLeft, motorPWM_FrontRight, motorPWM_RearRight);
		limitSwitchDoorOpened = new DigitalInput(Config.DIO_FLAP_OPENED);
		limitSwitchDoorClosed = new DigitalInput(Config.DIO_FLAP_CLOSED);
		launcher = new Launcher(motorPWM_Flywheel);
		ropeClimb = new RopeClimb(motorPWM_Flywheel2);
		intake = new Intake(motorPWM_Intake);
		agitator = new Agitator(motorPWM_Agitator);
		gateSub = new GateServo(gateServo);
		gateSub.changePos(Config.GATE_OPEN);
		gateSub.changePos(Config.GATE_CLOSED);
		MoveServo.position = Config.GATE_OPEN;
		new MoveServo().start();
		oi = new OI();
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{
	}
	
	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		Log.info("Autonomous Start!");
		// Log.info("Gyro was reset!");
		gateSub.changePos(Config.GATE_CLOSED);
		new AutoDrive(oi.autoChooser.getSelected()).start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		driveTrain = new DriveTrain(motorPWM_FrontLeft, motorPWM_RearLeft, motorPWM_FrontRight, motorPWM_RearRight);
		drive = new Drive(driveTrain, oi.getJoy(), Config.JOYSTICK_MODE);
		drive.start();
		intake = new Intake(motorPWM_Intake);
		// si=new SetIntakeSpeed(Config.INTAKE_MOTOR_SPEED);
		// si.start();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		// TODO uncomment ultrasonic code
		// Log.init("ultrasonic1");
		// if (ultrasonic != null) {
		// // ultrasonic.ping();
		//
		// Log.init("DiOutput" + digO.get());
		// Log.init("DiInput" + digI.get());
		// SmartDashboard.putNumber("Ultrasonic Sensor (in):",
		// ultrasonic.getRangeInches());
		// Log.init("" + ultrasonic.getRangeInches());
		// }
		/*
		 * if (ultrasonicAnalogInput != null) {
		 * SmartDashboard.putNumber("Ultrasonic Sensor (in):",
		 * ultrasonicAnalogInput.getAverageVoltage()); }
		 */
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
