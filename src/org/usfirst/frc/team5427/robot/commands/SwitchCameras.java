/**
 * 
 */
package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Blake Romero
 *
 */
public class SwitchCameras extends Command {

	public SwitchCameras()
	{
		
	}
	
	///Called just before the command runs the first time
	protected void initialize()
	{
		Log.init("Initialized SwitchCameras");
	}
	
	/*
	 * Method is called periodically during execution.
	 */
	protected void execute()
	{
		//changes the camera to the next camera - axis camera becomes camera 0
		Robot.currentCamera++;
		Robot.currentCamera%=3;
	}
	
	//Returns true when the command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	//Called once after isFinished returns true
	protected void end()
	{
		
	}
	
	//Calls when another subsystem is called
	@Override
	protected void interrupted()
	{
		end();
	}
}
