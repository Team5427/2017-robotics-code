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
	 * launcher constructor -- takes motors for various parts of the rope
	 * climber as parameters
	 * 
	 * @param motorFlyWheel
	 */
	public RopeClimb(SpeedController motorFlyWheel) {
		this.motorPWM_Flywheel = motorFlyWheel;

		Log.init("FINISHED MAKING A NEW RopeClimb");
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets all of the motor speeds to 0
	 */
	public void stop() {
		setPullSpeed(0);
	}

	public void stopPull() {
		setPullSpeed(0);
	}

	/**
	 * sets the speed of the pulling motors to the specified speed.
	 * 
	 * @param speed
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