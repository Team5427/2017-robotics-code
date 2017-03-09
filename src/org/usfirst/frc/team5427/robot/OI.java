package org.usfirst.frc.team5427.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team5427.robot.commands.AgitatorBack;
import org.usfirst.frc.team5427.robot.commands.AgitatorStart;
import org.usfirst.frc.team5427.robot.commands.AgiBack;
//import org.usfirst.frc.team5427.robot.commands.ChangeCameras
//import org.usfirst.frc.team5427.robot.commands.ChangeCamera;
import org.usfirst.frc.team5427.robot.commands.ChangeDirections;
import org.usfirst.frc.team5427.robot.commands.PullRope;
import org.usfirst.frc.team5427.robot.commands.SetFlapStage;
//import org.usfirst.frc.team5427.robot.commands.SetFlapStage;
import org.usfirst.frc.team5427.robot.commands.SetIntakeSpeed;
import org.usfirst.frc.team5427.robot.commands.ShooterStart;
import org.usfirst.frc.team5427.robot.subsystems.Agitator;
//import org.usfirst.frc.team5427.robot.commands.SwitchCameras;
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

	// Buttons
	/**
	 * Switches the camera view
	 */
	//public Button switchCameras = new JoystickButton(joy, Config.SWITCH_CAMERAS_BUTTON);
	/**
	 * Changes the direction of the intake
	 */
	public Button changeIntakeDirection = new JoystickButton(joy, Config.CHANGE_INTAKE_DIRECTION_BUTTON);
	/**
	 * Starts the intake
	 */
	public Button startIntake = new JoystickButton(joy, Config.START_INTAKE_BUTTON);
	/**
	 * Button to shoot the balls
	 */
	public Button shooter = new JoystickButton(joy, Config.SHOOT_BUTTON);
	/**
	 * Commands for moveable flap
	 */
	public Button flapOpen = new JoystickButton(joy, Config.FLAP_OPEN);
	public Button flapClose = new JoystickButton(joy, Config.FLAP_CLOSE);
	public SetIntakeSpeed si;

	/**button for rope climb*/
	public Button pull = new JoystickButton(joy, Config.PULL_BUTTON);
	/**button for agitator climb*/
	public Button spin = new JoystickButton(joy, Config.SPIN_BUTTON);
	public Button spinBack = new JoystickButton(joy, Config.SPIN_OUT_BUTTON);
	
	SendableChooser<Integer> autoChooser= new SendableChooser<Integer>();
	
	/**
	 * Constructor for the OI class, defines the button-press events.
	 */
	public OI() {
		shooter.whileHeld(new ShooterStart(Config.SHOOTER_MOTOR_SPEED));

		startIntake.toggleWhenPressed(new SetIntakeSpeed(Config.INTAKE_MOTOR_SPEED));
		changeIntakeDirection.toggleWhenPressed(new ChangeDirections());
		pull.whenPressed(new PullRope());
		spin.whileHeld(new AgitatorStart(Config.AGITATOR_SPEED));
		spinBack.whileHeld(new AgiBack(Config.AGITATOR_SPEED_BACKWARDS));
		flapOpen.whenPressed(new SetFlapStage(Config.stage.OPEN));
		flapClose.whenPressed(new SetFlapStage(Config.stage.CLOSE));

		
		autoChooser.addDefault("              ",	Config.AUTO_NONE);
		autoChooser.addObject("AutoDriveLeft  ", Config.AUTO_LEFT);
		autoChooser.addObject("AutoDriveMiddle", Config.AUTO_MIDDLE);
		autoChooser.addObject("AutoDriveRight ", Config.AUTO_RIGHT);
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
