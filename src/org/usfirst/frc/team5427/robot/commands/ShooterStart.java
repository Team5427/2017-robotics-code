package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterStart extends Command {

	private double shootSpeed;

	public ShooterStart() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.launcher);

	}

	/**
	 * sets the speed of the launching mechanism to the speed defined in the
	 * configuration file.
	 */
	protected void initialize() {
		Log.init("initialized Shoot");
		shootSpeed = Config.LAUNCH_SPEED;

		Robot.launcher.setShootSpeed(shootSpeed);
	}

	//sets shoot speed to speed specified in config
	protected void execute() {
		Robot.launcher.setShootSpeed(shootSpeed);
	}

	//returns true when the shoot button is released
	protected boolean isFinished() {
		if (!Robot.oi.getJoy().getRawButton(Config.SHOOT_BUTTON))
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.launcher.stopShoot();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
