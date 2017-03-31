package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GateServo extends Subsystem
{

	Servo servo;
	
	//Desired Position
	private double desPos;
	
	public GateServo(Servo servo)
	{
		this.servo = servo;
		servo.setSpeed(.3);
		changePos(Config.GATE_CLOSED);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void changePos(int position)
	{
		if(position==Config.GATE_CLOSED)
		{
			position=Config.GATE_OPEN;
			servo.set(.8);
			setDesPos(.8);
//			System.out.println("hOI im tEmmie");
		}
		else if(position==Config.GATE_OPEN)
		{
			position=Config.GATE_CLOSED;
			servo.set(.4);
			setDesPos(.4);
		}
	}

	public double getDesPos() {
		return desPos;
	}

	public void setDesPos(double desPos) {
		this.desPos = desPos;
	}
}
