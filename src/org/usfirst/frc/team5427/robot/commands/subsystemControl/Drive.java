
package org.usfirst.frc.team5427.robot.commands.subsystemControl;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

/**
 * this class constantly inputs the Joystick axis into the driveTrain file,
 * causing the robot to move.
 */
public class Drive extends Command {

	public static final int ONE_JOYSTICK = 0; // static var for above
	public static final int TWO_JOYSTICKS = 1; // static var for above

	private DriveTrain driveTrain;
	private Joystick joystick;
	private Joystick altJoystick;
	
	private int joystickMode;
	
	public Drive(DriveTrain driveTrain, Joystick joystick, int joystickMode) {
		// Use requires() here to declare subsystem dependencies
		this.driveTrain = driveTrain;
		this.joystick = joystick;
		this.joystickMode = joystickMode;
		this.altJoystick = null;
	}
	
	public Drive(DriveTrain driveTrain, Joystick joystick, Joystick altJoystick, int joystickMode) {
		// Use requires() here to declare subsystem dependencies
		this.driveTrain = driveTrain;
		this.joystick = joystick;
		this.joystickMode = joystickMode;
		this.altJoystick = altJoystick;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
	}

	// Called repeatedly when this Command is scheduled to run

	@SuppressWarnings("all")
	protected void execute() {
		
		if(Robot.oi.getJoy().getRawButton(Config.AUTO_ADJUST_BUTTON))
		{
			if(System.nanoTime()-Robot.swip.lastRecievedTime<500000000) //less than half a second since last recieved data
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
			}
		}
		else
		{
			if (joystickMode == ONE_JOYSTICK) {
				driveTrain.driveJoystick(joystick.getZ(), joystick.getY());
			}
			if (joystickMode == TWO_JOYSTICKS) {
				driveTrain.driveDualJoystick(joystick.getY(), altJoystick.getY());
			}
			// Log.init("DRIVING");
		}
		
	}

	//TODO Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
	
	public void autoAdjust()
	{
	}
}
