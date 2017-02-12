package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MultiFlap extends Subsystem{

	public SpeedController motorPWM_Flap;
	public Config.stage currentStage;
	
	public MultiFlap(SpeedController motorFlap) {
		this.motorPWM_Flap = motorFlap;
	}
		
	@Override
	protected void initDefaultCommand() {
		
	}
}
