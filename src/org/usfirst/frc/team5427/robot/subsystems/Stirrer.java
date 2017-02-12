package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem will be responsible for controlling the mechanism used to circulate
 * fuel in the ball storage area. It will be controlled with one motor.
 * 
 * @author Ethan Bennikutty
 *
 */
public class Stirrer extends Subsystem {

	/**
	 * SpeedController which is responsible for the blade that stir balls in
	 * the ball storage area
	 */
	public SpeedController motorPWM_Flywheel;

	/**
	 * Launcher Constructor - Initializes the motor used to control the blade.
	 * 
	 * @param motorFlyWheel - The motor used to control the blade.
	 */
	public Stirrer(SpeedController motorFlyWheel) {
		this.motorPWM_Flywheel = motorFlyWheel;
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the blade's motor's speed to zero.
	 */
	public void stop() {
		setSpinSpeed(0);

	}

	/**
	 * Sets the speed of the blades's motor to the desired speed.
	 * 
	 * @param speed - The desired speed of the blade.
	 */
	public void setSpinSpeed(double speed) {
		// Prevent speed from going to fast
		if (speed > 1)
			speed = 1;
		else if (speed < -1)
			speed = -1;

		motorPWM_Flywheel.set(speed);
	}

}