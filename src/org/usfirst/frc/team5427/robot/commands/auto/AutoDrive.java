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

	private boolean forward;

	public AutoDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		startTime= System.nanoTime();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
	}

	// Called repeatedly when this Command is scheduled to run

	@SuppressWarnings("all")
	protected void execute() {
			if(Config.left)
		{
			if(getTime<1000)
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(1);
			}
			else if(getTime<1500)
			{
				Robot.driveTrain.setLeftSpeed(-1);
				Robot.driveTrain.setRightSpeed(1);
			}
			else 
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(1);
			}
				
		}
		else if(Config.middle)
		{
			if(getTime()<500)
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(-1);
			}
			else
			{
			Robot.driveTrain.setLeftSpeed(-1);
			Robot.driveTrain.setRightSpeed(-1);
			}
		}
		else if(Config.right)
		{
			if(getTime<1000)
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(1);
			}
			else if(getTime<1500))
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(-1);
			}
			else 
			{
				Robot.driveTrain.setLeftSpeed(1);
				Robot.driveTrain.setRightSpeed(1);
			}
		}

	}
	protected int getTime() {
		return (int) ((System.nanoTime() - timeStarted) / 1000000);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Config.LEFT)
		{
			if(getTime()==5000)
			return true;
		return false;
		}
		if(Config.RIGHT)
		{
			if(getTime()==5000)
				return true;
			return false;
		}
		if(Config.MIDDLE)
		{
			if(getTime()==3000)
				return true;
			return false;
		}
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