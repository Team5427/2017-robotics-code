package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.SteelTalon;
import org.usfirst.frc.team5427.robot.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This Subsystem will be responsible for managing all four SIM motors that are
 * responsible for controlling the wheels. //TODO expand on this description
 * once more is known about the robot.
 * 
 * @author Andrew Kennedy, Bo Corman
 */
public class DriveTrain extends Subsystem {

	SpeedController motorPWM_FrontLeft;
	SpeedController motorPWM_RearLeft;
	SpeedController motorPWM_FrontRight;
	SpeedController MOTOR_PWM_BackRight;

	/**
	 * Drivetrain constructor -- as parameters takes each motor to initialise.
	 * 
	 * @param motorPWM_FrontLeft
	 * @param motorPWM_RearLeft
	 * @param motorPWM_FrontRight
	 * @param motorPWM_BackRight
	 */
	public DriveTrain(SpeedController motorPWM_FrontLeft, SpeedController motorPWM_RearLeft,
			SpeedController motorPWM_FrontRight, SpeedController motorPWM_BackRight) {
		this.motorPWM_FrontLeft = motorPWM_FrontLeft;
		this.motorPWM_RearLeft = motorPWM_RearLeft;
		this.motorPWM_FrontRight = motorPWM_FrontRight;
		this.MOTOR_PWM_BackRight = motorPWM_BackRight;
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the speed of the left motors on the drive train.
	 * 
	 * @param speed
	 *            - the speed you want to set
	 */
	public void setLeftSpeed(double speed) {
		motorPWM_FrontLeft.set(-speed);
		motorPWM_RearLeft.set(-speed);
	}

	/**
	 * Sets the speed of the right motors on the drive train.
	 * 
	 * @param speed
	 *            - the speed you want to set
	 */
	public void setRightSpeed(double speed) {
		motorPWM_FrontRight.set(speed);
		MOTOR_PWM_BackRight.set(speed);
	}

	/**
	 * Sets the speed of all motors to 0
	 */
	public void stop() {
		setLeftSpeed(0);
		setRightSpeed(0);
	}

	/**
	 * Takes the input of the joystick, and uses it to drive the robot. It
	 * currently uses six variables: the Z axis of the joystick, the Y axis of
	 * the joystick, the speed which the left (left) side should move, the speed
	 * at which the right side should move (right), the right side plus the left
	 * side (v), and the right side minus the left side (w).
	 * 
	 * @param z
	 *            - Z axis of joystick, positive is to the right
	 * @param y
	 *            - Y axis of joystick, positive is backwards
	 */
	public void driveJoystick(double z, double y) {

		z *= .6;

		y *= 1;

		/**
		 * This variable will be equal to the speed of the right side + the
		 * speed of the left side. It will be used in a systems of equations in
		 * order to calculate the right side.
		 */
		double v = (1 - Math.abs(z)) * y + y;
		/**
		 * This variable will be equal to the speed of the right side - the
		 * speed of the left side. It will be used in a systems of equations in
		 * order to calculate the left side.
		 */
		double w = (1 - Math.abs(y)) * z + z;
		/**
		 * Since v = R + L, and w = R - L we add the two variables together in
		 * order to get 2R + 0L, which we divide by two in order to get R.
		 */
		Robot.driveTrain.setRightSpeed((v + w) / 2);
		/**
		 * Since v = R + L, and w = R - L, we subtract the two variables in
		 * order to get 0R + 2L, which we then divide by two in order to get L.
		 */
		Robot.driveTrain.setLeftSpeed((v - w) / 2);

		/*
		 * //TODO delete old code once the new code is proven to work. double y
		 * = joyY; double z = joyZ; double left = 0; double right = 0; boolean b
		 * = false; if (z >= 0) { z = 1 - z; b = true; } else { z = z + 1; b =
		 * false; } if (b) { right = y * z; left = y; } else { left = y * z;
		 * right = y; } if (z != 0 && Math.abs(y) < .1) {
		 * 
		 * if (joyZ > .05) {
		 * 
		 * 
		 * right = joyZ * .7 ; left = -joyZ * .7;
		 * 
		 * 
		 * } else if (joyZ < -.05) {
		 * 
		 * right = joyZ * .7 ; left = -joyZ * .7;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * Robot.driveTrain.setLeftSpeed(left);
		 * Robot.driveTrain.setRightSpeed(right);
		 */

		/*
		 * double leftSpeed = (-1 * joyY) + joyX; double rightSpeed = (-1 *
		 * joyY) - joyX;
		 * 
		 * rightSpeed = 0 - rightSpeed; double max = Math.abs(leftSpeed); if
		 * (max < Math.abs(rightSpeed)) max = Math.abs(rightSpeed); if (max > 1)
		 * { leftSpeed = leftSpeed / max; rightSpeed = rightSpeed / max; }
		 * Robot.driveTrain.setLeftSpeed(leftSpeed);
		 * Robot.driveTrain.setRightSpeed(rightSpeed);
		 */

	}

	/**
	 * sets the left and right stick in accordance with the joystick inputs for
	 * dual joysticks
	 * 
	 * @param y
	 *            - Y axis of left joystick
	 * @param y2
	 *            - Y axis of right joystick
	 */
	public void driveDualJoystick(double y, double y2) {

		double leftSpeed = y;
		double rightSpeed = y2;
		Robot.driveTrain.setLeftSpeed(leftSpeed);
		Robot.driveTrain.setRightSpeed(rightSpeed);

	}

}
