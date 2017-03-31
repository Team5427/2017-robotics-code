package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.command.Command;

public class MoveServo extends Command
{
	public int position;
	public MoveServo()
	{
		requires(Robot.gateSub);
	}

	protected void initialize()
	{
		Log.init("Initialized MoveServo");
		if(position==1)
			position=Config.GATE_OPEN;
		else
			position=Config.GATE_CLOSED;
	}
	protected void execute()
	{
		Robot.gateSub.changePos(position);
	}
	@Override
	protected boolean isFinished()
	{
		if(Robot.gateSub.getDesPos()==Robot.gateServo.get())
			return true;
		else
			return false;
	}
	
	protected void end()
	{
		
	}

}
