package org.usfirst.frc.team5427.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

public class SetIntakeSpeed extends Command {

	//speed which the intake system runs at
	double speed;
	
	//
	boolean on = false;

	public SetIntakeSpeed(double speed) {
		//requires the intake system
		requires(Robot.intake);
		this.speed = speed;

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	//sets the speed to the speed specified in config
	@Override
	protected void execute() {
		Log.init("Intaking");
		Robot.intake.setSpeed(speed);
	}

	//turns off when the button to start intake is not pressed
	@Override
	protected boolean isFinished() {
		//if (Robot.oi.getJoy().getRawButton(Config.START_INTAKE_BUTTON))
			return false;
		//else
			//return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Log.init("Intake stopped");
		Robot.intake.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {

		end();
	}

}
