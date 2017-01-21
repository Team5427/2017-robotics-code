package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * the launcher subsystem is used for controlling the mechanism that launches
 * boulders, and involves 3 different parts, the shooting motors, turning
 * motors, and angling/tilting motors.
 * 
 * @author team5427
 *
 */
public class Launcher extends Subsystem {

	/**
	 * SpeedController which is responsible for the flywheel that launches the
	 * ball out of the robot.
	 */
	public SpeedController motorPWM_Flywheel;
	
	
	/**
	 * launcher constructor -- takes motors for various parts of the launcher as
	 * parameters
	 * 
	 * @param motorFlyWheel
	 */
	public Launcher(SpeedController motorFlyWheel) {
		this.motorPWM_Flywheel = motorFlyWheel;

		System.out.println("FINISHED MAKING A NEW LAUNCHER");
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets all of the motor speeds to 0
	 */
	public void stop() {
		setShootSpeed(0);

	}

public void stopShoot() {
		setShootSpeed(0);
	}


	/**
	 * sets the speed of the shooting motors to the specified speed.
	 * 
	 * @param speed
	 */
	public void setShootSpeed(double speed) {
		// Prevent speed from going to fast
		if (speed > 1)
			speed = 1;
		else if (speed < -1)
			speed = -1;

		motorPWM_Flywheel.set(speed);
	}
	
	//public double getDegrees() {
	
		//return Config.TURRET_CENTER - Robot.potentiometer.get();
//	}

}