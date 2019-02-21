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
	private double targetTime;
	private Config.stage target;
	private boolean open = false;
	
	
	public SetFlapStage(Config.stage target){
		//change directions requires the intake system
		requires(Robot.myFlap);
		
		this.target = target;
		
		if(target==Robot.myFlap.currentStage){
			targetTime = 0;
		}
		else if(target==Config.stage.OPEN||Robot.limitSwitchDoorOpened.get()){
			open = false;
			targetTime=Config.FLAP_OPEN_TIMEOUT;
		}
		else if(target==Config.stage.CLOSE||Robot.limitSwitchDoorClosed.get()){
			open = true;
			targetTime = Config.FLAP_CLOSE_TIMEOUT;
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
		if(isTimedOut())
			return true;
		if(open&&!Robot.limitSwitchDoorOpened.get())
			return true;
		if(!open&&!Robot.limitSwitchDoorClosed.get())
			return true;
		return false;
	}
	
	// protected void close(){
	// 	Robot.myFlap.motorPWM_Flap.set(Config.FLAP_SPEED);
	// }
	protected void open(){
		Robot.myFlap.motorPWM_Flap.set(-Config.FLAP_SPEED);
	}
	
	protected void end(){
		Robot.myFlap.motorPWM_Flap.stopMotor();
		Robot.myFlap.currentStage = target;
	}
}
