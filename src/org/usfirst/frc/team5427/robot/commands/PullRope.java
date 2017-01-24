package org.usfirst.frc.team5427.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;
import org.usfirst.frc.team5427.robot.Robot;
/**
 * The PullRope class is used to start and stop the motor(s) used to pull the rope into the robot.
 * @author Blake Romero
 *
 */
public class PullRope extends Command {
	
	//Holds the speed of the motors used to pull the rope into the robot.
	private double pullSpeed;
	
	/**
	 * sets the speed of the pulling mechanism to the speed defined in the
	 * configuration file.
	 */
	public PullRope() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ropeClimb);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Log.init("Initialized Pull");
		pullSpeed = Config.PULL_SPEED;

		Robot.ropeClimb.setPullSpeed(pullSpeed);
	}

	// Called repeatedly when this Command is scheduled to run

	protected void execute() {
		Robot.ropeClimb.setPullSpeed(pullSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(!Robot.oi.getJoy().getRawButton(Config.PULL_BUTTON)) return true;
		else return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.ropeClimb.stopPull();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
