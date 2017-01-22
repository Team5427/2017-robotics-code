package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.OurClasses.SteelTalon;
import org.usfirst.frc.team5427.robot.util.Config;

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
public class Intake extends Subsystem {

	SpeedController motorPWM_Intake;

	/**
	 * Drive Train constructor -- as parameters takes each motor to initialize.
	 * 
	 * @param motorPWM_FrontLeft
	 * @param motorPWM_RearLeft
	 * @param motorPWM_FrontRight
	 * @param motorPWM_BackRight
	 */
	public Intake(SpeedController motorPWM_Intake)
	{
			this.motorPWM_Intake=motorPWM_Intake;	
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
	public void setSpeed(double speed) {
		motorPWM_Intake.set(speed);
	}
	
	public void changeDirections()
	{
		motorPWM_Intake.set(-motorPWM_Intake.get());
	}

	/**
	 * Sets the speed of the right motors on the drive train.
	 * 
	 * @param speed
	 *            - the speed you want to set
	 */


	/**
	 * Sets the speed of all motors to 0
	 */
	public void stop() {
		setSpeed(0);
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
	

}
