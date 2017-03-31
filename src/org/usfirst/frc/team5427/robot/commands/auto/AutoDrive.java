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
		setTimeout(15);
	}
	private double mag=-.3;
	private double curve=.3;
	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
//		position = Config.BLUE_AUTO_LEFT;
		startTime=System.nanoTime();

		
		
	}

	//TODO check this code for autonomous
	// Called repeatedly when this Command is scheduled to run

	private double forwardStartTime = -1;
	private double time = 3.5;
	
	/**i m
	 * TODO comment
	 */
	@SuppressWarnings("all")
	protected void execute() {
		
		Log.info("Time:"+getTime());
		Log.info("Pos:"+position);
	
	
		if(position == Config.BLUE_AUTO_LEFT)
		{
			Log.info("BL");
			double timetoTurn = 7.8;
			if(getTime()<4.6)	{ 
				Robot.driveTrain.driveWPI(-0.3,.3);
			}
			else if(getTime()<4.6+3)	{ 
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			} else if (getTime() < timetoTurn) {
				Robot.driveTrain.driveWPI(0.3, 0);
			} else if (getTime() < (timetoTurn + .2)) {
				System.out.print("waiting to turn");
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			} else if (getTime() < (timetoTurn + .2 + 3.3)) {
				System.out.print("turning");
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT * -1);
				}
			else {
				end();
			}

		}
		else if(position == Config.BLUE_AUTO_MIDDLE)
		{
			//testing
		//blue mid
//			Log.init("Starting Blue Autonomous Middle");
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
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD_RIGHT);
			}
			else if(getTime()< Config.AUTO_MIDDLE_AFTER_BACK_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
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
					
//					if (t < 2f) {
//						Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
//					} else {
//						Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
//					}
			}
			else {
				end();
			}

		}
		else if(position == Config.BLUE_AUTO_RIGHT)
		{
			
			//blue right
			
			if(getTime()<5.6)	{ 
				Robot.driveTrain.driveWPI(-0.3,-.32);
			}
			else {
				end();
			}

		}
		else if(position == Config.RED_AUTO_RIGHT)
		{
			//red right
			double timetoTurnRed = 8.8;
			if(getTime() < 5.6)	{ 
				Robot.driveTrain.driveWPI(-0.3,-.32);
			}
			else if(getTime() < 5.6+3)	{ 
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}	
			else if(getTime() < timetoTurnRed)	{ 
				Robot.driveTrain.driveWPI(0.3,0);		}
			else if(getTime() < (timetoTurnRed + .2) )	{ 
				System.out.print("waiting to turn");
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime() < (timetoTurnRed + .2 + 3.3))	{ 
				System.out.print("turning");
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT * -1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT);
			}
			else {
				end();
			}
		}
		else if(position == Config.RED_AUTO_MIDDLE)
		{
			Log.init("Starting Red Autonomous Middle");
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
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_SPEED_BACKWARD_LEFT);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_SPEED_BACKWARD_RIGHT);
			}
			else if(getTime()< Config.AUTO_MIDDLE_AFTER_BACK_DELAY)
			{
				Robot.driveTrain.setLeftSpeed(0);
				Robot.driveTrain.setRightSpeed(0);
			}
			else if(getTime()<Config.AUTO_MIDDLE_TURN_TO_GOAL_TIME)
			{
				Robot.driveTrain.setLeftSpeed(Config.AUTO_FULL_TURN_SPEED_LEFT*-1);
				Robot.driveTrain.setRightSpeed(Config.AUTO_FULL_TURN_SPEED_RIGHT );
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
//				if (t < 2f) {
//					Robot.agitator.setSpinSpeed(-Config.AGITATOR_SPEED);
//				} else {
//					Robot.agitator.setSpinSpeed(Config.AGITATOR_SPEED);
//				}
			}
			else {
				end();
			}

		}
		else if(position == Config.RED_AUTO_LEFT)
		{
			//redLeft
		if(getTime()<4.6)	{ 
			Robot.driveTrain.driveWPI(-0.3, .3);
		} 
			end();
		}

	}

	
	
	/**
	 * 
	 * @return returns the number of milliseconds since autonomous started
	 */
	protected double getTime() {
		return (double) ((System.nanoTime() - startTime) / 1000000000f);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		if(getTime()>15)
		{
			return true;
		}
		
		
		
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
		Robot.launcher.stop();
//		Robot.agitator.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
