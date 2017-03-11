package org.usfirst.frc.team5427.robot.OurClasses;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.SpeedController;;

/**
 * Allows multiple PWMSpeedControllers for a single PIDOutput
 * 
 * @author Charlemagne Wong
 *
 */
public class SteelPIDOutput implements PIDOutput{

	SpeedController[] controllers;
	
	public SteelPIDOutput(SpeedController ... controllers)
	{
		this.controllers = controllers;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		for (SpeedController i : controllers) {
			i.pidWrite(output);
		}
	}
	
}