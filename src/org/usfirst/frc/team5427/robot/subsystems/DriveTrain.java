package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.OurClasses.SteelTalon;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;

/**
 * This Subsystem will be responsible for managing all four SIM motors that are
 * responsible for controlling the wheels. It contains initialization code, code
 * for setting speeds, and code for setting the speeds of individual sides.
 * There is no option for individual motors, so mecanum wheels cannot be
 * supported.
 * 
 * @author Andrew Kennedy, Bo Corman
 */
public class DriveTrain extends Subsystem {

	SpeedController motorPWM_FrontLeft;
	SpeedController motorPWM_RearLeft;
	SpeedController motorPWM_FrontRight;
	SpeedController MOTOR_PWM_BackRight;
	protected static boolean kArcadeRatioCurve_Reported = false;

	/**
	 * Drive Train Constructor - Uses the speed controllers to initialize the
	 * speed controllers used in the subsystem.
	 * 
	 * @param motorPWM_FrontLeft
	 *            - The motor in the front left position.
	 * @param motorPWM_Rearleft
	 *            - The motor in the back left position.
	 * @param motorPWM_FrontRight
	 *            - The motor in the front right position.
	 * @param motorPWM_BackRight
	 *            - The motor in the back right position.
	 */
	public static final double kDefaultSensitivity = 0.5;
	public static final double kDefaultMaxOutput = 1.0;
	protected double m_sensitivity;
	protected double m_maxOutput;

	public DriveTrain(SpeedController motorPWM_FrontLeft, SpeedController motorPWM_RearLeft,
			SpeedController motorPWM_FrontRight, SpeedController motorPWM_BackRight) {
		this.motorPWM_FrontLeft = motorPWM_FrontLeft;
		this.motorPWM_RearLeft = motorPWM_RearLeft;
		this.motorPWM_FrontRight = motorPWM_FrontRight;
		this.MOTOR_PWM_BackRight = motorPWM_BackRight;

		m_sensitivity = kDefaultSensitivity;
		m_maxOutput = kDefaultMaxOutput;
		// boolean m_allocatedSpeedControllers = true;
	}
//	RobotDrive robotDrive41 = new RobotDrive(0, 0);
	

	public void driveWPI(double outputMagnitude, double curve) {
		final double leftOutput;
		final double rightOutput;

		if (!kArcadeRatioCurve_Reported) {
			HAL.report(tResourceType.kResourceType_RobotDrive, getNumMotors(), tInstances.kRobotDrive_ArcadeRatioCurve);
			kArcadeRatioCurve_Reported = true;
		}
		if (curve < 0) {
			double value = Math.log(-curve);
			double ratio = (value - m_sensitivity) / (value + m_sensitivity);
			if (ratio == 0) {
				ratio = .0000000001;
			}
			leftOutput = outputMagnitude / ratio;
			rightOutput = outputMagnitude;
		} else if (curve > 0) {
			double value = Math.log(curve);
			double ratio = (value - m_sensitivity) / (value + m_sensitivity);
			if (ratio == 0) {
				ratio = .0000000001;
			}
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude / ratio;
		} else {
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude;
		}
		setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	protected int getNumMotors() {
		return 4;
	}

	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		// m_frontLeftMotor.set(limit(leftOutput) * m_maxOutput);
		setLeftSpeed(limit(leftOutput) * m_maxOutput);
		// m_frontRightMotor.set(-limit(rightOutput) * m_maxOutput);
		setRightSpeed(limit(rightOutput) * m_maxOutput);
	}

	protected static double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		if (num < -1.0) {
			return -1.0;
		}
		return num;
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the speed of the left side motors on the drive train.
	 * 
	 * @param speed
	 *            - The wanted speed of the left side motors.
	 */
	public void setLeftSpeed(double speed) {
		motorPWM_FrontLeft.set(-speed * Config.DRIVE_TRAIN_MULTIPLIER);
		motorPWM_RearLeft.set(-speed * Config.DRIVE_TRAIN_MULTIPLIER);
	}

	/**
	 * Sets the speed of the right side motors of the drive train.
	 * 
	 * @param speed
	 *            - The wanted speed of the right side motors.
	 */
	public void setRightSpeed(double speed) {
		Log.debug("Speed: " + speed);
		motorPWM_FrontRight.set(speed * Config.DRIVE_TRAIN_MULTIPLIER);
		MOTOR_PWM_BackRight.set(speed * Config.DRIVE_TRAIN_MULTIPLIER);
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
	 * at which the right side should move (right), the right side's speed plus
	 * the left side's speed (v), and the right side's speed minus the left
	 * side's speed (w).
	 * 
	 * @param z
	 *            - Z axis of joystick, positive is to the right
	 * @param y
	 *            - Y axis of joystick, positive is backwards
	 */
	public void driveJoystick(double z, double y) {
		if (Math.abs(y) <= Config.DEADSET_DIF)
			y = 0;
		if (Math.abs(z) <= Config.DEADSET_DIF)
			z = 0;
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
	 * @param y
	 *            - Y axis of the left joystick
	 * @param y2
	 *            - Y axis of the right joystick
	 */
	public void driveDualJoystick(double y, double y2) {
		double leftSpeed = y;
		double rightSpeed = y2;
		Robot.driveTrain.setLeftSpeed(leftSpeed);
		Robot.driveTrain.setRightSpeed(rightSpeed);
	}
}
