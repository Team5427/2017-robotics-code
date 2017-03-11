package org.usfirst.frc.team5427.robot.commands.auto;


import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

/**
 * this class constantly inputs the Joystick axis into the driveTrain file,
 * causing the robot to move.
 */
public class AutoDrive extends Command {

	private int position;
	private long startTime;
	
	public AutoDrive(int position) {
		// Use requires() here to declare subsystem dependencies
   
		requires(Robot.driveTrain);
		
		switch(position)
		{
		
		case Config.BLUE_AUTO_LEFT:
			Log.info("Chose Blue Auto_Left selection");
			break;
		case Config.BLUE_AUTO_MIDDLE:
			Log.info("Chose Blue Auto_Middle selection");
			break;
		case Config.BLUE_AUTO_RIGHT:
			Log.info("Chose Blue Auto_Right selection");
			break;
		case Config.RED_AUTO_LEFT:
			Log.info("Chose RED nAuto_Left selection");
			break;
		case Config.RED_AUTO_MIDDLE:
			Log.info("Chose RED Auto_Middle selection");
			break;
		case Config.RED_AUTO_RIGHT:
			Log.info("Chose RED Auto_Right selection");
			break;
		default:
			Log.info("Did not chose an Autonomous modee");
		}
		
		this.position = position;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
		startTime = System.nanoTime();
	}

	//TODO check this code for autonomous
	// Called repeatedly when this Command is scheduled to run

	private double forwardStartTime = -1;
	
	/**
	 * TODO comment
	 */
	@SuppressWarnings("all")
	protected void execute() {
		
		Log.info("Time:"+getTime());
		Log.info("Pos:"+position);
	
		if(position == Config.BLUE_AUTO_LEFT)
		{
			if(getTime()<Config.AUTO_LEFT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_LEFT_BEFORE_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_TURN_TIME)
			{
				Robot.driveTrain.setLeftSpeed(-.3);
				Robot.driveTrain.setRightSpeed(.3);
			}
			else if(getTime()<Config.AUTO_LEFT_AFTER_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_LEFT_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
//			else if(getTime()<Config.AUTO_LEFT_BACK_OFF_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
//				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
//			}
//			else if(getTime()<Config.AUTO_LEFT_TURN_TO_GOAL_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
//				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
//			}
//			else if(getTime()<Config.AUTO_LEFT_SHOOT_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(0);
//				Robot.driveTrain.setRightSpeed(0);
//				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
//			}
		}
		else if(position == Config.BLUE_AUTO_MIDDLE)
		{
			int horizontal =0; //TODO: GET ANGLE FROM CHARLIE
			Log.init("Starting Autonomous Middle");
			if(getTime()<=Config.AUTO_MIDDLE_START_DRIVE_TIME)
			{
//				Log.info("DRIVING FORWARD");
				
				if (forwardStartTime == -1) {
					forwardStartTime = getTime();
					Log.debug("Start forward time: " + forwardStartTime);
				}
				
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_GEAR_WAIT_TIME)
			{
				Log.debug("Time finished: " + getTime() + " Time difference: " + (getTime() - forwardStartTime));
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
//				return;
			}
			else if(getTime()<Config.AUTO_MIDDLE_BACK_OFF_TIME)
			{
				Log.debug("Middle back wait time: " + Config.AUTO_MIDDLE_BACK_OFF_TIME);
				Robot.driveTrain.setLeftSpeed(.26);
				Robot.driveTrain.setRightSpeed(.30);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
			}
			else if (getTime() < Config.AUTO_MIDDLE_TURN_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if (getTime() < Config.AUTO_MIDDLE_DRIVE_GOAL_TIME) 
			{
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_SHOOT_TIME)
			{
				if(getTime()-Robot.swip.lastRecievedTime<500000000) //less than half a second since last recieved data
				{
					double horAngle = Robot.swip.horizontalAngle;  //horizontal angle from center
					
					if(horAngle<-1*Config.DEGREE_THRESHOLD) //robot is to the left
					{
						//TODO: replace values with config values
						if(horAngle>-5)
							Robot.driveTrain.setLeftSpeed(.1);
						else if(horAngle>-10)
							Robot.driveTrain.setLeftSpeed(.4);
						else
							Robot.driveTrain.setLeftSpeed(.7);
					}
					else if(horAngle>Config.DEGREE_THRESHOLD) //robot is to the right
					{
						//TODO: replace values with config values
						if(horAngle>5)
							Robot.driveTrain.setLeftSpeed(.1);
						else if(horAngle>10)
							Robot.driveTrain.setLeftSpeed(.4);
						else
							Robot.driveTrain.setLeftSpeed(.7);
					}
					else if(horAngle==Config.DEGREE_THRESHOLD)
					{
						Robot.driveTrain.setLeftSpeed(0);
						Robot.driveTrain.setRightSpeed(0);
						Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
						double firstTime=getTime();
						while(getTime()-firstTime<=5&&getTime()<Config.AUTO_MIDDLE_SHOOT_TIME)
						{
							if(0==(int)(Math.abs(getTime()-firstTime))%2)
								Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
							else if(1==(int)(Math.abs(getTime()-firstTime)%2))
								Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
						}
					}
				}
				
				
			}
				
			else
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(0);
				Robot.agitator.setSpinSpeed(0);
			
			}
				
		}
		else if(position == Config.BLUE_AUTO_RIGHT)
		{
			if(getTime()<Config.AUTO_RIGHT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_RIGHT_TURN_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
			}
			else if(getTime()<Config.AUTO_RIGHT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
//			else if(getTime()<Config.AUTO_RIGHT_GEAR_WAIT_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(0);
//				Robot.driveTrain.setRightSpeed(0);
//			}
//			else if(getTime()<Config.AUTO_RIGHT_BACK_OFF_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
//				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
//			}
//			else if(getTime()<Config.AUTO_RIGHT_TURN_TO_GOAL_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
//				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
//			}
//			else if(getTime()<Config.AUTO_RIGHT_SHOOT_TIME)
//			{
//				Robot.driveTrain.setLeftSpeed(0);
//				Robot.driveTrain.setRightSpeed(0);
//				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
//			}
		}
		//TODO THIS IS THE CODE FOR THE RED SIDE. FOR NOW JUST A COPY PASTE. STILL NEED TO CHANGE VALUES AND TEST\
		else if(position == Config.RED_AUTO_RIGHT)
		{
			//drive forwards for an amount of time
			if(getTime()<Config.AUTO_LEFT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_LEFT_TURN_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
			}
			else if(getTime()<Config.AUTO_LEFT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_LEFT_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_BACK_OFF_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
			}
			else if(getTime()<Config.AUTO_LEFT_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
				//TODO Make this work with vision processing
			}
			else if(getTime()<Config.AUTO_LEFT_SHOOT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
			}
		}
		else if(position == Config.RED_AUTO_MIDDLE)
		{
			Log.init("Starting Autonomous Middle");
			if(getTime()<=Config.AUTO_MIDDLE_START_DRIVE_TIME)
			{
//				Log.info("DRIVING FORWARD");
				
				if (forwardStartTime == -1) {
					forwardStartTime = getTime();
					Log.debug("Start forward time: " + forwardStartTime);
				}
				
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_GEAR_WAIT_TIME)
			{
				Log.debug("Time finished: " + getTime() + " Time difference: " + (getTime() - forwardStartTime));
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
//				return;
			}
			else if(getTime()<Config.AUTO_MIDDLE_BACK_OFF_TIME)
			{
				Log.debug("Middle back wait time: " + Config.AUTO_MIDDLE_BACK_OFF_TIME);
				Robot.driveTrain.setLeftSpeed(.26);
				Robot.driveTrain.setRightSpeed(.30);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
			}
			else if (getTime() < Config.AUTO_MIDDLE_TURN_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if (getTime() < Config.AUTO_MIDDLE_DRIVE_GOAL_TIME) 
			{
				Robot.driveTrain.setLeftSpeed(-.25);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_SHOOT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
				double firstTime=getTime();
				while(getTime()-firstTime<=5&&getTime()<Config.AUTO_MIDDLE_SHOOT_TIME)
				{
					if(0==(int)(Math.abs(getTime()-firstTime))%2)
						Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
					else if(1==(int)(Math.abs(getTime()-firstTime)%2))
						Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
				}
			}
			else
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(0);
				Robot.agitator.setSpinSpeed(0);
			
			}
				
		}
		else if(position == Config.RED_AUTO_LEFT)
		{
			if(getTime()<Config.AUTO_RIGHT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_RIGHT_TURN_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT*-1);
			}
			else if(getTime()<Config.AUTO_RIGHT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_RIGHT_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_RIGHT_BACK_OFF_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
			}
			else if(getTime()<Config.AUTO_RIGHT_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
			}
			else if(getTime()<Config.AUTO_RIGHT_SHOOT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
			}
		}

	}
	
	/**
	 * 
	 * @return returns the number of miliseconds since autonomous started
	 */
	protected double getTime() {
		return (double) ((System.nanoTime() - startTime) / 1000000000f);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (position == Config.BLUE_AUTO_LEFT)
		{
			if(getTime()>=Config.LEFT_TIMEOUT)
			return true;
		return false;
		}
		if(position == Config.BLUE_AUTO_MIDDLE)
		{
			if(getTime()>=Config.MIDDLE_TIMEOUT)
				return true;
			return false;
		}
		if(position == Config.BLUE_AUTO_RIGHT)
		{
			if(getTime()>=Config.RIGHT_TIMEOUT)
				return true;
			return false;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
		Robot.launcher.stop();
		Robot.agitator.stop();
	
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}