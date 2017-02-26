package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class AgitatorStart extends Command {

	//Speed of the blades.
	private double spinSpeed;

	/**
	 * Sets the speed of the agitator to the speed defined in the
	 * configuration file.
	 */
	public AgitatorStart(double spinSpeed) {
		// Use requires() here to declare subsystem dependencies
		this.spinSpeed=spinSpeed;
		requires(Robot.agitator);

	}

	/**
	 * Sets the speed of the agitator to the speed defined in the
	 * configuration file.
	 */
	protected void initialize() {
		Log.init("initialized Spin");


		Robot.agitator.setSpinSpeed(spinSpeed);
	}

	//sets spin speed to speed specified in config
	protected void execute() {
		Robot.agitator.setSpinSpeed(spinSpeed);
	}

	//returns true when the shoot button is released
	protected boolean isFinished() {
		//if (!Robot.oi.getJoy().getRawButton(Config.SPIN_BUTTON))
	//		return true;
		//else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.agitator.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
