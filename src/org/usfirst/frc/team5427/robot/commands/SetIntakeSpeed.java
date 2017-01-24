package org.usfirst.frc.team5427.robot.commands;

	import edu.wpi.first.wpilibj.command.Command;

	import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Log;

public class SetIntakeSpeed extends Command{

		double speed=0;
		boolean on=false;
		
		public SetIntakeSpeed(double speed) {
			// Use requires() here to declare subsystem dependencies
			requires(Robot.intake);
			this.speed=speed;
			
		}

		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			Log.init("Intaking");
			Robot.intake.setSpeed(speed);
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return false;
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
