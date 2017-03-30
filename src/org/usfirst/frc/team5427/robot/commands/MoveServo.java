package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class MoveServo extends Command
{
	
	public MoveServo()
	{
		requires(Robot.gateSub);
	}

	protected void initialize()
	{
		Log.init("Initialized MoveServo");
	}
	protected void execute()
	{
		Robot.gateSub.changePos();
	}
	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	protected void end()
	{
		
	}

}
