package org.usfirst.frc.team5427.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This file //TODO finish this
 * 
 * @author Ethan Bennikutty
 *
 */

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

public class SetFlapStage extends Command{
	private long startTime;
	private long endTime;
	private long targetTime;
	private Config.stage target;
	private boolean open = false;
	
	
	public SetFlapStage(Config.stage target){
		//change directions requires the intake system
		requires(Robot.mFlap);
		
		this.target = target;
		
		if(target==Robot.mFlap.currentStage){
			targetTime = 0;
		}
		if(target==Config.stage.OPEN){
			open = false;
			targetTime=Config.TIMEOUT_FLAP_CLOSE;
		}
		if(target==Config.stage.CLOSE){
			targetTime = Config.TIMEOUT_FLAP_OPEN
			open = true;
		}
		
		setTimeout(targetTime);
	}
	
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize(){
		
		
	}

	//changes the direction of the intake
	@Override
	protected void execute(){
		if(open)
			open();
		else
			close();
	}
	
	@Override
	protected boolean isFinished(){
		return isTimedOut()|| !Robot.limitSwitchDoor.get();
	}
	
	protected void close(){
		Robot.mFlap.motorPWM_Flap.set(Config.FLAP_SPEED);
	}
	protected void open(){
		Robot.mFlap.motorPWM_Flap.set(-Config.FLAP_SPEED);
	}
	
	protected void end(){
		Robot.mFlap.motorPWM_Flap.stopMotor();
		Robot.mFlap.currentStage = target;
	}
}
