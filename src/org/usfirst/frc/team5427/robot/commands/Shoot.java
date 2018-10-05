package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {

	//speed of the shooter
	private double shootSpeed;

	/**
	 * sets the speed of the launching mechanism to the speed defined in the
	 * configuration file.
	 */
	public Shoot(double shootSpeed) {
		// Use requires() here to declare subsystem dependencies
		this.shootSpeed=shootSpeed;
		requires(Robot.launcher);

	}

	/**
	 * sets the speed of the launching mechanism to the speed defined in the
	 * configuration file.
	 */
	protected void initialize() {
		Robot.launcher.setShootSpeed(shootSpeed);
	}

	//sets shoot speed to speed specified in config
	protected void execute() {
		Robot.launcher.setShootSpeed(shootSpeed);
	}

	//returns true when the shoot button is released
	protected boolean isFinished() {
		return (!Robot.oi.getJoy().getRawButton(Config.SHOOT_BUTTON));
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.launcher.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
