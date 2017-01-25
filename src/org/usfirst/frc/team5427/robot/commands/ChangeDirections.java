package org.usfirst.frc.team5427.robot.commands;

	import edu.wpi.first.wpilibj.command.Command;

	import org.usfirst.frc.team5427.robot.Robot;

public class ChangeDirections extends Command{
		
		public ChangeDirections() {
			// Use requires() here to declare subsystem dependencies
			requires(Robot.intake);
		}

		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			Robot.intake.changeDirections();
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return false;
		}

		// Called once after isFinished returns true
		@Override
		protected void end() {
			Robot.intake.stop();
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
				end();
		}

}
