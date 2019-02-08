package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class NetworkedCommands extends Command{
	private int commandButton;
	//private double timeout;
	private double driveZOrSpeed;  //stores joystick Z value for drive train, or speed for other variables
	private double driveY;
	

	
	
	public NetworkedCommands(int commandButton, double timeout, double driveZOrSpeed, double driveY) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		requires(Robot.launcher);
		requires(Robot.ropeClimb);
		requires(Robot.intake);
		requires(Robot.agitator);
		
		this.commandButton=commandButton;
		//this.timeout=timeout;
		this.driveZOrSpeed=driveZOrSpeed;;
		this.driveY=driveY;
		
		setTimeout(timeout);
		Log.info("NetworkedCommand created");

		//TODO limit the commands included in execute

}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//side note:
//		public static final int SHOOT_BUTTON = 1;
//		public static final int PULL_BUTTON = 8;
//		public static final int START_INTAKE_BUTTON = 4;
//		public static final int CHANGE_INTAKE_DIRECTION_BUTTON = 6;
//		public static final int SPIN_BUTTON = 2;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(0==commandButton)
		{Robot.driveTrain.driveJoystick(driveZOrSpeed, driveY);}
		else if(Config.SHOOT_BUTTON==commandButton)
		{Robot.launcher.setShootSpeed(driveZOrSpeed);}
		else if(Config.PULL_BUTTON==commandButton)
		{new PullRope();}
		else if(Config.SPIN_BUTTON==commandButton)
		{new AgitatorStart(Config.AGITATOR_SPEED);}
		else if(Config.START_INTAKE_BUTTON==commandButton)
		{new SetIntakeSpeed(Config.INTAKE_MOTOR_SPEED);}
		else if(Config.CHANGE_INTAKE_DIRECTION_BUTTON==commandButton)
		{new ChangeDirections();}
		Log.info("Excecuting NetworkedCommand");

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(isTimedOut())
			return true;
		//the below is just an idea; it does not have to be used
//		if(timeOut<0.2)
//			return true;
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		if(0==commandButton)
		{Robot.driveTrain.stop();}
		else if(Config.SHOOT_BUTTON==commandButton)
		{Robot.launcher.stop();}
		else if(Config.PULL_BUTTON==commandButton)
		{Robot.ropeClimb.stop();}
		else if(Config.SPIN_BUTTON==commandButton)
		{Robot.agitator.stop();}
		else if(Config.START_INTAKE_BUTTON==commandButton)
		{Robot.intake.stop();}
		else if(Config.CHANGE_INTAKE_DIRECTION_BUTTON==commandButton)
		{Robot.intake.stop();}
		Log.info("NetworkedCommand should have ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
