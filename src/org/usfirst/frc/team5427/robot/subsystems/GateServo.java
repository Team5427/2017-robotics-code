package org.usfirst.frc.team5427.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GateServo extends Subsystem
{

	Servo servo;
	int position;
	
	public GateServo(Servo servo)
	{
		this.servo = servo;
		this.position = 1;
		servo.set(.6);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void changePos()
	{
		if(position==1)
		{
			position=2;
			servo.set(.4);
		}
		else if(position==2)
		{
			position=1;
			servo.set(.6);
		}
	}
}
