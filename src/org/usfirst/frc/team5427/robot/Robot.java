
package org.usfirst.frc.team5427.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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

import org.usfirst.frc.team5427.robot.commands.subsystemControl.*;

import org.usfirst.frc.team5427.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot{

	public static OI oi;

	// PWM Motors for Drive Train
	/**
	 * Motor utilized in the DriveTrain. It is located in the front left of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	static SpeedController motorPWM_FrontLeft;

	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the rear left of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	static SpeedController motorPWM_RearLeft;

	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the front right of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	static SpeedController motorPWM_FrontRight;

	// TODO fill in the blank in this comment after testing the robot.
	/**
	 * Motor utilized in the DriveTrain. It is located in the rear right of the
	 * robot from a top-down point of view, and setting the speed of this motor
	 * to a positive value will cause the robot to move __________
	 */
	static SpeedController motorPWM_RearRight;
	
	/**
	 * DriveTrain subsytem to control the drive train
	 */
	static SpeedController motorPWM_Flywheel;
	/**
	 * motor for shooter
	 */
	public static Launcher launcher;
	/**
	 * 
	 */
	public static DriveTrain driveTrain;

	public static Drive drive;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/* Sensors */
	/**
	 * Ultrasonic Range Finder to find the distance between the sensor and target
	 */
	public static Ultrasonic ultrasonic = new Ultrasonic(Config.ULTRASONIC_PING_CHANNEL, Config.ULTRASONIC_ECHO_CHANNEL);;
	public static AnalogInput ultrasonicAnalogInput = new AnalogInput(0);
	
	CameraServer server;
	//these are the two usb cameras
	UsbCamera usbCam0, usbCam1;
	
	AxisCamera axisCam;
	
	//NI USB interface numbers for the cameras
	//int devForCam0=2,devForCam1=3;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		/* Initialize Steel Talon Motors */
		Log.init("Initializing SteelTalon Motors");
		
		Log.init("Initializing Drive Train");
		motorPWM_RearRight = new SteelTalon(Config.REAR_RIGHT_MOTOR, 0, 0);
		motorPWM_FrontRight = new SteelTalon(Config.FRONT_RIGHT_MOTOR, 0, 0);
		motorPWM_RearLeft = new SteelTalon(Config.REAR_LEFT_MOTOR, 0, 0);
		motorPWM_FrontLeft = new SteelTalon(Config.FRONT_LEFT_MOTOR, 0, 0);
		driveTrain = new DriveTrain(motorPWM_FrontLeft, motorPWM_RearLeft, motorPWM_FrontRight, motorPWM_RearRight);
		Log.init("driveTrain initialized!");
		
		Log.init("Initializing Flywheels");
		motorPWM_Flywheel = new SteelTalon(Config.SHOOTER_MOTOR);
		
		Log.init("Initialized all SteelTalon Motors!");
		
		
		/* Initialize Subsystem*/
		Log.init("Initializing Subsystems");

		Log.init("Initializing Launcher subsystem");
		launcher = new Launcher(motorPWM_Flywheel);
		Log.init("Launcher subsystem Initialized!");
		
		
		/* Initialize Sensor */
		
		// Ultrasonic
		ultrasonic.setAutomaticMode(true);
		
		server = CameraServer.getInstance();
		
		//creates camera 0 (the smaller one) and adds it to the server
		usbCam0 = new UsbCamera("cam0", 0);
		server.addCamera(usbCam0);
		
		//creates camera 1 (the larger one) and adds it to the server
		usbCam1 = new UsbCamera("cam1", 1);
		server.addCamera(usbCam1);
		
		//axisCam = new AxisCamera("axisCamera", "10.54.27.11");
		//server.addCamera(axisCam);
		
		//Starts video of both cameras
//		server.startAutomaticCapture(usbCam0);
//		server.startAutomaticCapture(usbCam1);
//		server.startAutomaticCapture(axisCam);
		
		server.addServer("Camera0");
		//server.putVideo("cam0");
		
		//in the dashboard, select 'cam0' and 'cam1'
		
		server.putVideo("cam0", 20, 20);
		
		//CameraServer.getInstance().startAutomaticCapture(usbCam0);
		
	
		Log.init("Initializing OI");
		oi = new OI();
		Log.init("OI Initialized!");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
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
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		driveTrain = new DriveTrain(motorPWM_FrontLeft, motorPWM_RearLeft, motorPWM_FrontRight, motorPWM_RearRight);
		
		drive = new Drive(driveTrain, oi.getJoy(), Config.JOYSTICK_MODE);
		drive.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		if (ultrasonic != null) {
//			SmartDashboard.putNumber("Ultrasonic Sensor (in):", ultrasonic.getRangeInches());Voltage
			
		}
		if (ultrasonicAnalogInput != null) {
			SmartDashboard.putNumber("Ultrasonic Sensor (in):", ultrasonicAnalogInput.getAverageVoltage());
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	
}