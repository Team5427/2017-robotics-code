package org.usfirst.frc.team5427.robot.commands;

	import edu.wpi.first.wpilibj.command.Command;

	import org.usfirst.frc.team5427.robot.Robot;
	import org.usfirst.frc.team5427.robot.util.Config;

public class ChangeDirections extends Command{
		
		public ChangeDirections() {
			//change directions requires the intake system
			requires(Robot.intake);
		}

		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
		}

		//changes the direction of the intake
		@Override
		protected void execute() {
			//Robot.intake.changeDirections();
			Robot.intake.setSpeed(Config.INTAKE_MOTOR_SPEED_BACKWARDS);
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			if (!Robot.oi.getJoy().getRawButton(Config.CHANGE_INTAKE_DIRECTION_BUTTON))
				return true;
			return false;
		}

		//stops the intake system
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
