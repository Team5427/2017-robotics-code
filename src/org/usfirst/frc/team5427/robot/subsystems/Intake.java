package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.OurClasses.SteelTalon;
import org.usfirst.frc.team5427.robot.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This Subsystem will be responsible for managing the motor used to control the Intake mechanism.
 * 
 * @author 
 */
public class Intake extends Subsystem {

	SpeedController motorPWM_Intake;

	/**
	 * Intake subsystem constructor. - Initializes the Speed Controller for the motor used to
	 * control the intake.
	 * 
	 * @param motorPWM_Intake - The motor used to control the intake.
	 */
	public Intake(SpeedController motorPWM_Intake)
	{
			this.motorPWM_Intake=motorPWM_Intake;	
	}

	@Override
	protected void initDefaultCommand() {
		
		}

	/**
	 * Sets the speed of the intake motor.
	 * 
	 * @param speed - The speed you want to set the motor at.
	 */
	public void setSpeed(double speed) {
		motorPWM_Intake.set(speed);
	}
	
	/**
	 * Changes the direction of the intake motor.
	 */
	public void changeDirections()
	{
		motorPWM_Intake.set(-motorPWM_Intake.get());
	}

	/**
	 * Sets the speed of the motor to 0.
	 */
	public void stop() {
		setSpeed(0);
	}
}
