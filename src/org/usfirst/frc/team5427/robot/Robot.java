
package org.usfirst.frc.team5427.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team5427.robot.OurClasses.*;

//import org.usfirst.frc.team5427.robot.commands.ExampleCommand;
import org.usfirst.frc.team5427.robot.util.Log;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.commands.ChangeCamera;
import org.usfirst.frc.team5427.robot.commands.SetIntakeSpeed;
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
	
	//motor for intake
	static SpeedController motorPWM_Intake;

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
	 * DriveTrain subsystem to control the drive train
	 */
	public static DriveTrain driveTrain;

	public static Intake intake;

	
	public static Drive drive;
	public static SetIntakeSpeed si;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static CameraServer server;
	//these are the two usb cameras
	public static UsbCamera usbCam0, usbCam1;
	
	//this is the ip camera
	public static AxisCamera axisCam;
	
	public static RobotCameras roboCams;
	public static int currentCamera = 0;
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


		
		
		
		
		motorPWM_RearRight = new SteelTalon(Config.REAR_RIGHT_MOTOR, 0, 0);
		motorPWM_FrontRight = new SteelTalon(Config.FRONT_RIGHT_MOTOR, 0, 0);
		motorPWM_RearLeft = new SteelTalon(Config.REAR_LEFT_MOTOR, 0, 0);
		motorPWM_FrontLeft = new SteelTalon(Config.FRONT_LEFT_MOTOR, 0, 0);
		motorPWM_Intake = new SteelTalon(Config.INTAKE_MOTOR, 0, 0);
		
		
		intake=new Intake(motorPWM_Intake);
		Log.info("Intake SUbsystem Initialized!");
		driveTrain = new DriveTrain(motorPWM_FrontLeft, motorPWM_RearLeft, motorPWM_FrontRight, motorPWM_RearRight);
		Log.init("driveTrain initialized!");
		server = CameraServer.getInstance();
		axisCam = new AxisCamera("axisCamera", "10.54.27.11");
	//	server.addCamera(axisCam);
		
		//creates camera 0 (the smaller one) and adds it to the server
		usbCam0 = new UsbCamera("cam0", 0);
		usbCam0.setFPS(30);
	//	server.addCamera(usbCam0);
		
		//creates camera 1 (the larger one) and adds it to the server
		usbCam1 = new UsbCamera("cam1", 1);
		usbCam1.setFPS(30);

		//server.addCamera(usbCam1);
		
		
		roboCams=new RobotCameras(usbCam0, usbCam1, axisCam);

		

	//	server.startAutomaticCapture(roboCams.getCurrentCamera());
				
		//CameraServer.getInstance().startAutomaticCapture();
		
//		MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1180);
//		mjpegServer1.setSource(usbCam0);
		

	//	server.startAutomaticCapture(usbCam0);
	//		server.startAutomaticCapture(usbCam1);
//SmartDashboard.putData("Image", usbCam0.);
		

		
/*		CvSink cvSink = new CvSink ("opencv_USB Camera 0");
		cvSink.setSource(usbCam0);
		CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 30);
		MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1180);
		mjpegServer2.setSource(outputStream);*/
		
		//server.addCamera(usbCam1);
		
		//Starts video of both cameras
		//server.startAutomaticCapture(usbCam1);
//		server.startAutomaticCapture(axisCam);
		
		//server.addServer("Camera0");
		//server.putVideo("cam0");
		
		//in the dashboard, select 'cam0' and 'cam1'
		
		//server.putVideo("cam0", 20, 20);
		
		//CameraServer.getInstance().startAutomaticCapture(usbCam0);
	//	chooser.addObject("Cam",new ChangeCamera());

		//SmartDashboard sd = new SmartDashboard();
		//SmartDashboard sd2 = new SmartDashboard();
		/*CvSink cvSink = new CvSink ("opencv_USB Camera 0");
		cvSink.setSource(usbCam0);
		CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 30);
		MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1180);
		mjpegServer2.setSource(outputStream);
		server.addServer(mjpegServer2);*/
		Robot.server.addCamera(usbCam0);	Robot.server.addCamera( usbCam1);Robot.server.addCamera(axisCam);
		if(Robot.roboCams.currentCamera==0)
		{
			
			//Robot.server.removeCamera("axisCam");
			
			Robot.server.putVideo("usbCam0", 200, 200);
			Robot.server.startAutomaticCapture(Robot.usbCam0);
		}
		else if(Robot.roboCams.currentCamera==1)
		{
		
			//Robot.server.removeCamera("usbCam0");
		
				Robot.server.putVideo("usbCam1", 200, 200);
			Robot.server.startAutomaticCapture(Robot.usbCam1);
			
		}
		else if(Robot.roboCams.currentCamera==2)
		{
	
			//Robot.server.removeCamera("usbCam1");
			
			Robot.server.putVideo("axisCamera", 200, 200);
			Robot.server.startAutomaticCapture(Robot.axisCam);
		}
	//	chooser.addObject("camera", server);
		SmartDashboard.putData("Auto mode",chooser);
		oi = new OI();
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
		
		intake=new Intake(motorPWM_Intake);
		//si=new SetIntakeSpeed(Config.INTAKE_MOTOR_SPEED);
		//si.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//Robot.server.addCamera(Robot.roboCams.getCurrentCamera());
		//Robot.server.startAutomaticCapture(Robot.roboCams.getCurrentCamera());		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	
}