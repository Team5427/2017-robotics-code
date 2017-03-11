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

	private SpeedController front;
	private SpeedController rear;
	
	public SteelPIDOutput(SpeedController front, SpeedController rear)
	{
		this.front=front;
		this.rear=rear;
	}
	
	@Override
	public void pidWrite(double output) {
		front.pidWrite(output);
		//rear.pidWrite(-output);
	}
	
}