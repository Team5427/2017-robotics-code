package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RopeClimb subsystem is used to control the motors used in the PullRope
 * command, which is used to control the motors used to climb the rope.
 * 
 * @author Blake Romero
 *
 */
public class RopeClimb extends Subsystem {

	/**
	 * SpeedControllers which are responsible for the wheels that pull the rope
	 * into the robot.
	 */
	public SpeedController motorPWM_Flywheel;

	/**
	 * RopeClimb constructor -- Initializes the motor for the RopeClimb subsystem.
	 * 
	 * @param motorFlyWheel - The motor used to control the RopeClimb.
	 */
	public RopeClimb(SpeedController motorFlyWheel) {
		this.motorPWM_Flywheel = motorFlyWheel;
		Log.init("FINISHED MAKING A NEW RopeClimb");
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the motor's speed to zero.
	 */
	public void stop() {
		setPullSpeed(0);
	}

	/**
	 * Sets the speed of the RopeClimb to the desired speed.
	 * 
	 * @param speed - The desired speed of the RopeClimb.
	 */
	public void setPullSpeed(double speed) {
		// Prevent speed from going to fast
		if (speed > 1)
			speed = 1;
		else if (speed < -1)
			speed = -1;

		motorPWM_Flywheel.set(speed);
	}
}