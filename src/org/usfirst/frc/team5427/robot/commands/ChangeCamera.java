package org.usfirst.frc.team5427.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Log;

public class ChangeCamera extends Command {
	
		boolean shouldFinish=false; 
		
		public ChangeCamera() {
			// Use requires() here to declare subsystem dependencies
			requires(Robot.roboCams);
			
		}

		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			Log.init("ChangeCamera");
			Robot.roboCams.changeCamera();
			Robot.server.addCamera(Robot.roboCams.getCurrentCamera());
			Robot.server.startAutomaticCapture(Robot.roboCams.getCurrentCamera());
			shouldFinish=true;
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return shouldFinish;
		}

		// Called once after isFinished returns true
		@Override
		protected void end() {
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
		}


}
