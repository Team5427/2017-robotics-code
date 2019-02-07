package org.usfirst.frc.team5427.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5427.robot.commands.ClimbDown;
import org.usfirst.frc.team5427.robot.commands.MoveServo;
import org.usfirst.frc.team5427.robot.commands.ClimbUp;
import org.usfirst.frc.team5427.robot.commands.IntakeIn;
import org.usfirst.frc.team5427.robot.commands.IntakeOut;
import org.usfirst.frc.team5427.robot.commands.Shoot;
import org.usfirst.frc.team5427.robot.subsystems.Agitator;
import org.usfirst.frc.team5427.robot.util.Config;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	/**
	 * Primary joystick
	 */
	public Joystick joy = new Joystick(Config.JOYSTICK_PORT);
	/**
	 * Alternate joystick. Currently unused
	 */
	public Joystick altJoy = new Joystick(Config.ALT_JOYSTICK_PORT);

	public Button shooter = new JoystickButton(joy, Config.SHOOT_BUTTON);
	public Button intakeIn = new JoystickButton(joy,Config.INTAKE_IN_BUTTON);
	public Button intakeOut = new JoystickButton(joy,Config.INTAKE_OUT_BUTTON);
	public Button climbUp = new JoystickButton(joy, Config.CLIMB_UP_BUTTON);
	public Button climbDown = new JoystickButton(joy, Config.CLIMB_DOWN_BUTTON);
	public Button moveServo = new JoystickButton(joy, Config.SERVO_SWITCH_BUTTON);
	
	SendableChooser<Integer> autoChooser= new SendableChooser<Integer>();
	
	/**
	 * Constructor for the OI class, defines the button-press events.
	 */
	public OI() {
		shooter.whileHeld(new Shoot(Config.SHOOTER_MOTOR_SPEED));
		intakeIn.whileHeld(new IntakeIn());
		intakeOut.whileHeld(new IntakeOut());
		climbUp.whileHeld(new ClimbUp());
		climbDown.whileHeld(new ClimbDown());
		moveServo.whenPressed(new MoveServo());
		autoChooser.addDefault("              ",	Config.AUTO_NONE);
		autoChooser.addObject("BlueAutoDriveLeft  ", Config.BLUE_AUTO_LEFT);
		autoChooser.addObject("BlueAutoDriveMiddle", Config.BLUE_AUTO_MIDDLE);
		autoChooser.addObject("BlueAutoDriveRight ", Config.BLUE_AUTO_RIGHT);
		autoChooser.addObject("RedAutoDriveLeft ", Config.RED_AUTO_LEFT);
		autoChooser.addObject("RedAutoDriveMiddle ", Config.RED_AUTO_MIDDLE);
		autoChooser.addObject("RedAutoDriveRight ", Config.RED_AUTO_RIGHT);
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
		// TODO tie the right buttons to the right commands
	}

	/**
	 * returns the joystick object
	 * @return the joystick
	 */
	public Joystick getJoy() {
		return joy;
	} 

	/**
	 * returns the right joystick if using 2 NOTE: not used for real, but used
	 * elsewhere in code
	 * @return the other joystick
	 */
	public Joystick getAltJoy() {
		return altJoy;

	}
}
