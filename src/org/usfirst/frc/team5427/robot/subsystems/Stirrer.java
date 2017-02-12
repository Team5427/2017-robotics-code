package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem will be responsible for controlling the mechanism used to shoot fuel
 * into the boilers. It will be controlled with one motor.
 * 
 * @author Ethan Bennikutty
 *
 */
public class Stirrer extends Subsystem {

	/**
	 * SpeedController which is responsible for the wheels that launch the
	 * balls out of the robot.
	 */
	public SpeedController motorPWM_Flywheel;

	/**
	 * Launcher Constructor - Initializes the motor used to control the launcher.
	 * 
	 * @param motorFlyWheel - The motor used to control the launcher.
	 */
	public Stirrer(SpeedController motorFlyWheel) {
		this.motorPWM_Flywheel = motorFlyWheel;
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the launcher's motor's speed to zero.
	 */
	public void stop() {
		setShootSpeed(0);

	}

	/**
	 * Sets the speed of the launcher's motor to the desired speed.
	 * 
	 * @param speed - The desired speed of the launcher.
	 */
	public void setShootSpeed(double speed) {
		// Prevent speed from going to fast
		if (speed > 1)
			speed = 1;
		else if (speed < -1)
			speed = -1;

		motorPWM_Flywheel.set(speed);
	}

}