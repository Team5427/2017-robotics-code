package org.usfirst.frc.team5427.robot.OurClasses;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PWMSpeedController;;

/**
 * Allows multiple PWMSpeedControllers for a single PIDOutput
 * 
 * @author Charlemagne Wong
 *
 */
public class SteelPIDOutput implements PIDOutput{

	PWMSpeedController[] controllers;
	
	public SteelPIDOutput(PWMSpeedController... controllers)
	{
		this.controllers = controllers;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		for (PWMSpeedController i : controllers) {
			i.pidWrite(output);
		}
	}
	
}