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
		startTime = System.nanoTime();
		this.position = position;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
	}

	//TODO check this code for autonomous
	// Called repeatedly when this Command is scheduled to run

	@SuppressWarnings("all")
	protected void execute() {
		if(position == Config.AUTO_LEFT)
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
		else if(position == Config.AUTO_MIDDLE)
		{
			if(getTime()<Config.AUTO_MIDDLE_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_BACK_OFF_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
			}
			else if(getTime()<Config.AUTO_MIDDLE_SHOOT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
			}
		}
		else if(position == Config.AUTO_RIGHT)
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
	protected int getTime() {
		return (int) ((System.nanoTime() - startTime) / 1000000);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (position == Config.AUTO_LEFT)
		{
			if(getTime()>=Config.LEFT_TIMEOUT)
			return true;
		return false;
		}
		if(position == Config.AUTO_MIDDLE)
		{
			if(getTime()>=Config.MIDDLE_TIMEOUT)
				return true;
			return false;
		}
		if(position == Config.AUTO_RIGHT)
		{
			if(getTime()>=Config.RIGHT_TIMEOUT)
				return true;
			return false;
		}
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}