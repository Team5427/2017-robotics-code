package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.OurClasses.SteelTalon;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This Subsystem will be responsible for managing all four SIM motors that are
 * responsible for controlling the wheels. It contains initialization code, code for setting speeds,
 * and code for setting the speeds of individual sides. There is no option for individual motors,
 * so mecanum wheels cannot be supported. 	
 * 
 * @author Andrew Kennedy, Bo Corman
 */
public class DriveTrain extends Subsystem {

	SpeedController motorPWM_FrontLeft;
	SpeedController motorPWM_RearLeft;
	SpeedController motorPWM_FrontRight;
	SpeedController MOTOR_PWM_BackRight;

	/**
	 * Drive Train Constructor - Uses the speed controllers to initialize the speed controllers used in the subsystem.
	 * 
	 * @param motorPWM_FrontLeft - The motor in the front left position.
	 * @param motorPWM_Rearleft - The motor in the back left position.
	 * @param motorPWM_FrontRight - The motor in the front right position.
	 * @param motorPWM_BackRight - The motor in the back right position.
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
	 * Sets the speed of the left side motors on the drive train.
	 * 
	 * @param speed - The wanted speed of the left side motors.
	 */
	public void setLeftSpeed(double speed) {
		motorPWM_FrontLeft.set(-speed*Config.DRIVE_TRAIN_MULTIPLIER);
		motorPWM_RearLeft.set(-speed*Config.DRIVE_TRAIN_MULTIPLIER);
	}

	/**
	 * Sets the speed of the right side motors of the drive train.
	 * 
	 * @param speed - The wanted speed of the right side motors.
	 */
	public void setRightSpeed(double speed) {
		Log.debug("Speed: " + speed);
		motorPWM_FrontRight.set(speed*Config.DRIVE_TRAIN_MULTIPLIER);
		MOTOR_PWM_BackRight.set(speed*Config.DRIVE_TRAIN_MULTIPLIER);
	}

	/**
	 * Sets the speed of all drive train motors to 0.
	 */
	public void stop() {
		setLeftSpeed(0);
		setRightSpeed(0);
	}

	/**
	 * Takes the input of the joystick, and uses it to drive the robot. It
	 * currently uses six variables: the Z axis of the joystick, the Y axis of
	 * the joystick, the speed which the left (left) side should move, the speed
	 * at which the right side should move (right), the right side's speed plus the left
	 * side's speed (v), and the right side's speed minus the left side's speed (w).
	 * 
	 * @param z - Z axis of joystick, positive is to the right
	 * @param y - Y axis of joystick, positive is backwards
	 */
	public void driveJoystick(double z, double y) {
		if(Math.abs(y)<=Config.DEADSET_DIF)
			y=0;
		if(Math.abs(z)<=Config.DEADSET_DIF)
			z=0;
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
	}

	/**
	 * Sets the left and right stick in accordance with the joystick inputs for
	 * dual joysticks.
	 * 
	 * @param y - Y axis of the left joystick
	 * @param y2 - Y axis of the right joystick
	 */
	public void driveDualJoystick(double y, double y2) {
		double leftSpeed = y;
		double rightSpeed = y2;
		Robot.driveTrain.setLeftSpeed(leftSpeed);
		Robot.driveTrain.setRightSpeed(rightSpeed);
	}
}
