//package org.usfirst.frc.team5427.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *This file //TODO finish this
// * 
// * @author Ethan Bennikutty
// *
// */
//
//import org.usfirst.frc.team5427.robot.Robot;
//import org.usfirst.frc.team5427.robot.util.Config;
//import org.usfirst.frc.team5427.robot.util.Log;
//
//public class SetFlapStage extends Command{
//	private long startTime;
//	private long endTime;
//	private long targetTime;
//	private Config.stage target;
//	private boolean up = false;
//	
//	
//	public SetFlapStage(Config.stage target){
//		//change directions requires the intake system
//		requires(Robot.mFlap);
//		
//		this.target = target;
//	}
//	
//	
//	// Called just before this Command runs the first time
//	@Override
//	protected void initialize(){
//		if(target==Robot.mFlap.currentStage){
//			targetTime = 0;
//		}
//		if(target==Config.stage.RETRACTED){
//			up = false;
//			if(Robot.mFlap.currentStage==Config.stage.GEAR)
//				targetTime = Config.gearToRetracted;
//			if(Robot.mFlap.currentStage==Config.stage.INTAKE)
//				targetTime = Config.retractedToIntake;
//		}
//		if(target==Config.stage.GEAR){
//			up = true;
//			if(Robot.mFlap.currentStage==Config.stage.RETRACTED)
//				targetTime = Config.gearToRetracted;
//			if(Robot.mFlap.currentStage==Config.stage.INTAKE)
//				targetTime = Config.intakeToGear;
//		}
//		if(target==Config.stage.INTAKE){
//			if(Robot.mFlap.currentStage==Config.stage.GEAR){
//				targetTime = Config.intakeToGear;
//				up = false;
//			}
//			if(Robot.mFlap.currentStage==Config.stage.RETRACTED){
//				targetTime = Config.retractedToIntake;
//				up = true;
//			}
//		}
//		startTime = System.nanoTime();
//		endTime = startTime+targetTime;
//	}
//
//	//changes the direction of the intake
//	@Override
//	protected void execute(){
//		if(up)
//			raise();
//		else
//			lower();
//	}
//	
//	@Override
//	protected boolean isFinished(){
//		if(System.nanoTime()>=endTime)
//			return true;
//		else
//			return false;
//	}
//	
//	protected void lower(){
//		Robot.mFlap.motorPWM_Flap.set(Config.FLAP_SPEED);
//	}
//	protected void raise(){
//		Robot.mFlap.motorPWM_Flap.set(-Config.FLAP_SPEED);
//	}
//	
//	protected void end(){
//		Robot.mFlap.motorPWM_Flap.stopMotor();
//		Robot.mFlap.currentStage = target;
//	}
//}
