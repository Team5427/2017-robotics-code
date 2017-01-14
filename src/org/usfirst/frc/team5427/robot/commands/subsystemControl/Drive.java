
package org.usfirst.frc.team5427.robot.commands.subsystemControl;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

/**
 * this class constantly inputs the Joystick axis into the driveTrain file,
 * causing the robot to move.
 */
public class Drive extends Command {

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("initialized Drive");
	}

	// Called repeatedly when this Command is scheduled to run

	@SuppressWarnings("all")
	protected void execute() {
		if (Robot.oi.getJoy().getRawButton(Config.TO_TURRET_BUTTON) == false) {
			if (Config.JOYSTICK_MODE == Config.ONE_JOYSTICK) {
				Robot.driveTrain.driveJoystick(Robot.oi.getJoy().getZ(), Robot.oi.getJoy().getY());
			}
			if (Config.JOYSTICK_MODE == Config.TWO_JOYSTICKS) {
				Robot.driveTrain.driveDualJoystick(Robot.oi.getJoy().getY(), Robot.oi.getAltJoy().getY());
			}
			// Log.init("DRIVING");
		} else {
			Robot.driveTrain.stop();
			// Log.init("NOT");

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
