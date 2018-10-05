package org.usfirst.frc.team5427.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

public class IntakeIn extends Command {

	//speed which the intake system runs at
	double speed = 0.3;

	public IntakeIn() {
		requires(Robot.intake);

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	//sets the speed to the speed specified in config
	@Override
	protected void execute() {
		Robot.intake.setSpeed(speed);
	}

	//turns off when the button to start intake is not pressed
	@Override
	protected boolean isFinished() {
		return (!Robot.oi.getJoy().getRawButton(Config.INTAKE_IN_BUTTON));
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intake.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

}
