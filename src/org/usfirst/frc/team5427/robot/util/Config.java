package org.usfirst.frc.team5427.robot.util;
//TODO FIXED CODE fEB 20 2017
/**
 * This file will store ALL of the variables, offsets, measurements, etc. that
 * our robot will use during the year. All variables are to be static, and
 * nothing in this file should ever have to be initiated.
 * 
 * @author Andrew Kennedy, Bo Corman
 *
 */
public class Config {

	/**
	 * <p>
	 * The name of the program that will be used in the console, or anywhere
	 * else applicable.
	 * </p>
	 */
	public static final String PROGRAM_NAME = "Team5427RoboCode";
	/**
	 * <p>
	 * If true, then every call to <code>Log.debug()</code> will be printed in
	 * the console.
	 * </p>
	 * <p>
	 * If false, then all calls to this method will be ignored, saving the
	 * console from any spam created from debugging.
	 * </p>
	 */
	public static final boolean DEBUG_MODE = true;
	/**
	 * <p>
	 * This controls all print statements sent through <code>Log.java</code>,
	 * besides <code>Log.error</code> and <code>Log.fatal</code>.
	 * </p>
	 * <p>
	 * If true, then all printing not excluded by this method will be displayed
	 * in the console. If false, it will not.
	 * </p>
	 */
	public static final boolean LOGGING = true; // only logs errors and fatals
												// with this false
	// Speeds for the different things that the robot needs to do
	// Controlled by grip
												// later

	// PWM PORTS
	// TODO reassign motor values	
	public static final int FRONT_RIGHT_MOTOR 	= 0;
	public static final int REAR_RIGHT_MOTOR	= 1;
	public static final int FRONT_LEFT_MOTOR	= 2;
	public static final int REAR_LEFT_MOTOR 	= 3;
	public static final int SHOOTER_MOTOR		= 4;	
	public static final int INTAKE_MOTOR 		= 5;
	public static final int AGITATOR_MOTOR 		= 6;
	public static final int ROPE_CLIMB_MOTOR    = 7;


	public static final double DRIVE_TRAIN_MULTIPLIER = 1;
	
	// Motor Bias
	public static final double BIAS_FRONT_LEFT_MOTOR_FORWARD  = .87;
	public static final double BIAS_FRONT_LEFT_MOTOR_BACKWARD = .94192;

	public static final double BIAS_REAR_LEFT_MOTOR_FORWARD    = .87;
	public static final double BIAS_REAR_LEFT_MOTOR_BACKWARD   = .94192;

	public static final double BIAS_FRONT_RIGHT_MOTOR_FORWARD  = 1;
	public static final double BIAS_FRONT_RIGHT_MOTOR_BACKWARD = 1;

	public static final double BIAS_REAR_RIGHT_MOTOR_FORWARD   = 1;
	public static final double BIAS_REAR_RIGHT_MOTOR_BACKWARD  = 1;

	public static final double BIAS_SHOOTER_MOTOR_FORWARD  	   = 1;
	public static final double BIAS_SHOOTER_MOTOR_BACKWARD     = 1;

	public static final double BIAS_INTAKE_MOTOR_FORWARD       = 1;
	public static final double BIAS_INTAKE_MOTOR_BACKWARD      = 1;

	// Motor Offset
	public static final double OFFSET_FRONT_LEFT_MOTOR_FORWARD  = 0;
	public static final double OFFSET_FRONT_LEFT_MOTOR_BACKWARD = 0;

	public static final double OFFSET_REAR_LEFT_MOTOR_FORWARD    = 0;
	public static final double OFFSET_REAR_LEFT_MOTOR_BACKWARD   = 0;

	public static final double OFFSET_FRONT_RIGHT_MOTOR_FORWARD  = 0;
	public static final double OFFSET_FRONT_RIGHT_MOTOR_BACKWARD = 0;

	public static final double OFFSET_REAR_RIGHT_MOTOR_FORWARD   = 0;
	public static final double OFFSET_REAR_RIGHT_MOTOR_BACKWARD  = 0;

	public static final double OFFSET_SHOOTER_MOTOR_FORWARD  = 0;
	public static final double OFFSET_SHOOTER_MOTOR_BACKWARD = 0;

	public static final double OFFSET_INTAKE_MOTOR_FORWARD  = 0;
	public static final double OFFSET_INTAKE_MOTOR_BACKWARD = 0;

	// motor speeds

	public static final double INTAKE_MOTOR_SPEED = .80;
	public static final double INTAKE_MOTOR_SPEED_BACKWARDS=-.1;
	public static final double SHOOTER_MOTOR_SPEED = -1;
	public static final double AGITATOR_SPEED = .5;
	public static final double PULL_SPEED = -1; 

	/* ----------Controller Ports---------- */
	// Joystick
	public static final int JOYSTICK_PORT = 0;
	public static final int ALT_JOYSTICK_PORT = 0;
	public static final int ONE_JOYSTICK = 0; // static var for above
	public static final int TWO_JOYSTICKS = 1; // static var for above
	public static final int JOYSTICK_MODE = ONE_JOYSTICK; // Set this to either
															// ONE_JOYSTICK or
	
	/*------------speed for auto-------*/
	public static final double AUTO_FULL_SPEED_FORWARD = .1;
	public static final double AUTO_FULL_SPEED_BACKWARD = .1;
	public static final double AUTO_FULL_TURN_SPEED_RIGHT = .1;
	public static final double AUTO_FULL_TURN_SPEED_LEFT = .1;	

	
	/*------------timings for auto-------*/
	//General Constants
	public static final double AUTO_GEAR_WAIT_TIME = 0;
	public static final double AUTO_BACK_OFF_TIME = 0;
	public static final double AUTO_SHOOT_TIME = 0;
	public static final double LEFT_TIMEOUT = 60;
	public static final double MIDDLE_TIMEOUT = 60;
	public static final double RIGHT_TIMEOUT = 60;
	//Left Side
	public static final double AUTO_LEFT_START_DRIVE_TIME = 0;
	public static final double AUTO_LEFT_TURN_TIME = AUTO_LEFT_START_DRIVE_TIME+0;
	public static final double AUTO_LEFT_DRIVE_TO_GEAR_TIME =AUTO_LEFT_TURN_TIME+0;
	public static final double AUTO_LEFT_GEAR_WAIT_TIME = AUTO_LEFT_DRIVE_TO_GEAR_TIME+AUTO_GEAR_WAIT_TIME;
	public static final double AUTO_LEFT_BACK_OFF_TIME = AUTO_LEFT_GEAR_WAIT_TIME+0;//+AUTO_BACK_OFF_TIME;
	public static final double AUTO_LEFT_TURN_TO_GOAL_TIME =AUTO_LEFT_BACK_OFF_TIME+ 0;
	public static final double AUTO_LEFT_SHOOT_TIME =AUTO_LEFT_TURN_TO_GOAL_TIME+ 0;//+AUTO_SHOOT_TIME;
	//Middle
	public static final double AUTO_MIDDLE_START_DRIVE_TIME = 0;
	public static final double AUTO_MIDDLE_GEAR_WAIT_TIME = AUTO_MIDDLE_START_DRIVE_TIME+AUTO_GEAR_WAIT_TIME;
	public static final double AUTO_MIDDLE_BACK_OFF_TIME = AUTO_MIDDLE_GEAR_WAIT_TIME+0;//+AUTO_BACK_OFF_TIME;
	public static final double AUTO_MIDDLE_TURN_TO_GOAL_TIME =AUTO_MIDDLE_BACK_OFF_TIME+ 0;
	public static final double AUTO_MIDDLE_SHOOT_TIME =AUTO_MIDDLE_TURN_TO_GOAL_TIME+ 0;//+AUTO_SHOOT_TIME;
	//Right Side
	public static final double AUTO_RIGHT_START_DRIVE_TIME = 0;
	public static final double AUTO_RIGHT_TURN_TIME = AUTO_RIGHT_START_DRIVE_TIME+0;
	public static final double AUTO_RIGHT_DRIVE_TO_GEAR_TIME =AUTO_RIGHT_TURN_TIME+0;
	public static final double AUTO_RIGHT_GEAR_WAIT_TIME = AUTO_RIGHT_DRIVE_TO_GEAR_TIME+AUTO_GEAR_WAIT_TIME;
	public static final double AUTO_RIGHT_BACK_OFF_TIME = AUTO_RIGHT_GEAR_WAIT_TIME+0;//+AUTO_BACK_OFF_TIME;
	public static final double AUTO_RIGHT_TURN_TO_GOAL_TIME =AUTO_RIGHT_BACK_OFF_TIME+ 0;
	public static final double AUTO_RIGHT_SHOOT_TIME =AUTO_RIGHT_TURN_TO_GOAL_TIME+ 0;//+AUTO_SHOOT_TIME;

	
	/*-----------spot-----------*/
	public static final int AUTO_LEFT = 0;
	public static final int AUTO_MIDDLE = 1;
	public static final int AUTO_RIGHT = 2;
	
	/*---------------Buttons------------------- */
	public static final int SHOOT_BUTTON = 1;
	public static final int PULL_BUTTON = 8;
	public static final int SWITCH_CAMERAS_BUTTON = 3;
	public static final int START_INTAKE_BUTTON = 4;
	public static final int CHANGE_INTAKE_DIRECTION_BUTTON = 6;
	public static final int SPIN_BUTTON = 2;
	public static final int FLAP_REATRACTED = 7;
	public static final int FLAP_GEAR = 9;
	public static final int FLAP_INTAKE=11;

	/*-------------Ultrasonic Sensor---------*/
	public static final int ULTRASONIC_PING_CHANNEL = 0;
	public static final int ULTRASONIC_ECHO_CHANNEL = 1;

	/*------------Mulipurpose Flap Vars------*/
	public static enum stage {RETRACTED,INTAKE,GEAR};
	public static final long retractedToIntake = 1;
	public static final long intakeToGear = 1;
	public static final long gearToRetracted = 1;
	public static final double FLAP_SPEED = .05;
}
