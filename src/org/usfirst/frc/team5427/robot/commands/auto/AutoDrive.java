package org.usfirst.frc.team5427.robot.commands.auto;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private boolean gyroReset = false;
	private boolean turnTimeSet=false;
	private long agitatorTime = 0;
	
	public AutoDrive(int position) {
		// Use requires() here to declare subsystem dependencies
   
		requires(Robot.driveTrain);
		requires(Robot.launcher);
		requires(Robot.agitator);
		
//		switch(position)
//		{
//		
//		case Config.BLUE_AUTO_LEFT:
//			Log.info("Chose Blue Auto_Left selection");
//			break;
//		case Config.BLUE_AUTO_MIDDLE:
//			Log.info("Chose Blue Auto_Middle selection");
//			break;
//		case Config.BLUE_AUTO_RIGHT:
//			Log.info("Chose Blue Auto_Right selection");
//			break;
//		case Config.RED_AUTO_LEFT:
//			Log.info("Chose RED nAuto_Left selection");
//			break;
//		case Config.RED_AUTO_MIDDLE:
//			Log.info("Chose RED Auto_Middle selection");
//			break;
//		case Config.RED_AUTO_RIGHT:
//			Log.info("Chose RED Auto_Right selection");
//			break;
//		default:
//			Log.info("Did not chose an Autonomous modee");
//		}
		
		this.position = position;
		setTimeout(15);
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
		SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
		
		if(position == Config.BLUE_AUTO_LEFT)
		{
			if(getTime()<Config.AUTO_LEFT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()<Config.AUTO_LEFT_BEFORE_TURN_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}

				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_TURN_TO_GEAR_TIME)
			{				
				while(Robot.gyro.getAngle()>-Config.A_RT_TURN_GEAR_DEG)
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
				}
				Config.AUTO_LEFT_TURN_TO_GEAR_TIME=getTime();
			}
			else if(getTime()< Config.AUTO_LEFT_AFTER_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else
			{end();}


		}
		else if(position == Config.BLUE_AUTO_MIDDLE)
		{
			Log.init("Starting Autonomous Middle");
			if(getTime()<Config.AUTO_MIDDLE_START_DRIVE_TIME)
			{				
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()<Config.AUTO_MIDDLE_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_BACK_OFF_TIME)
			{
				Robot.driveTrain.setLeftSpeed(.26);
				Robot.driveTrain.setRightSpeed(.30);
			}
			else if(getTime()< Config.AUTO_MIDDLE_AFTER_BACK_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}
				
				//Log.debug("Time finished: " + getTime() + " Time difference: " + (getTime() - forwardStartTime));
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				while(Robot.gyro.getAngle()<Config.MIDDLE_TURN_ANGLE)
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
				}
				Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME=getTime();
			}
			else if (getTime() < Config.AUTO_MIDDLE_TURN_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_DRIVE_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime() < 15 /*<Config.AUTO_MIDDLE_SHOOT_TIME*/)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
					double t = (double)(getTime() - Config.AUTO_MIDDLE_DRIVE_GOAL_TIME) % 3.5;
					
					if (t < 2f) {
						Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
					} else {
						Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
					}
			}
			else
			{end();}

				
		}
		else if(position == Config.BLUE_AUTO_RIGHT)
		{
			if(getTime()<Config.AUTO_RIGHT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()< Config.AUTO_RIGHT_BEFORE_TURN_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_RIGHT_TURN_TO_GEAR_TIME)
			{
				while(Robot.gyro.getAngle()<Config.A_RT_TURN_GEAR_DEG)
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
				}
				Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME=getTime();
			}
			else if(getTime()< Config.AUTO_RIGHT_AFTER_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_RIGHT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else
			{end();}


		}
		else if(position == Config.RED_AUTO_RIGHT)
		{
			if(getTime()<Config.AUTO_RIGHT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()< Config.AUTO_RIGHT_BEFORE_TURN_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}
				
				//Log.debug("Time finished: " + getTime() + " Time difference: " + (getTime() - forwardStartTime));
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_RIGHT_TURN_TO_GEAR_TIME)
			{				
				while(Robot.gyro.getAngle()<Config.A_RT_TURN_GEAR_DEG)
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
				}
				Config.AUTO_RIGHT_TURN_TO_GEAR_TIME=getTime();
			}
			else if(getTime()< Config.AUTO_RIGHT_AFTER_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_RIGHT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else
			{end();}
		}
		else if(position == Config.RED_AUTO_MIDDLE)
		{
			Log.init("Starting Autonomous Middle");
			if(getTime()<Config.AUTO_MIDDLE_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()<Config.AUTO_MIDDLE_GEAR_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_BACK_OFF_TIME)
			{
				Robot.driveTrain.setLeftSpeed(.26);
				Robot.driveTrain.setRightSpeed(.30);
			}
			else if(getTime()< Config.AUTO_MIDDLE_AFTER_BACK_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}
				
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				while(Robot.gyro.getAngle() > -(97.6 - 29))
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT* -1);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
				}	
				Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME=getTime();
			}
			else if (getTime() < Config.AUTO_MIDDLE_TURN_WAIT_TIME)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_DRIVE_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime() < 15 /*<Config.AUTO_MIDDLE_SHOOT_TIME*/)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
				Robot.launcher.setShootSpeed(Config.SHOOTER_MOTOR_SPEED);
//				double firstTime=getTime();
				
				/*if (getTime() < Config.AUTO_MIDDLE_DRIVE_GOAL_TIME+2)
				{
					Robot.agitator.setSpinSpeed(0);
				} else*/
					double t = (double)(getTime() - Config.AUTO_MIDDLE_DRIVE_GOAL_TIME) % 3.5;
					
					if (t < 2f) {
						Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
					} else {
						Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
					}
			}
			else
			{end();}

		}
		else if(position == Config.RED_AUTO_LEFT)
		{
			if(getTime()<Config.AUTO_LEFT_START_DRIVE_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else if(getTime()<Config.AUTO_LEFT_BEFORE_TURN_DELAY)
			{
				if (!gyroReset) {
					Robot.gyro.reset();
					gyroReset = true;
				}

				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_TURN_TO_GEAR_TIME)
			{				
				while(Robot.gyro.getAngle()>-Config.A_RT_TURN_GEAR_DEG)
				{
					SmartDashboard.putNumber("Gryoscope: ", Robot.gyro.getAngle());
					Log.debug("Turning robot - Angle: " + Robot.gyro.getAngle());
					Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
					Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
				}
				Config.AUTO_LEFT_TURN_TO_GEAR_TIME=getTime();
			}
			else if(getTime()< Config.AUTO_LEFT_AFTER_TURN_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_LEFT_DRIVE_TO_GEAR_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_FORWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_FORWARD_RIGHT);
			}
			else
			{end();}

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
		if(isTimedOut())
			return true;
		
		
		
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