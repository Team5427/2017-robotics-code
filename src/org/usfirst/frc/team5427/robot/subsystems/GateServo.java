package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GateServo extends Subsystem
{

	Servo servo;
	private double desPos;
	
	public GateServo(Servo servo)
	{
		this.servo = servo;
		servo.setSpeed(.3);
		changePos(1);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void changePos(int position)
	{
		if(position==1)
		{
			position=2;
			servo.set(.8);
			setDesPos(.8);
			
//			while(servo.get()>.4)
//			{
//				
//			}
//			servo.setSpeed(0);
			System.out.println("hOI im tEmmie");
		}
		else if(position==2)
		{
			position=1;
			servo.set(.4);
			setDesPos(.4);
			//servo.setSpeed(.3);
			System.out.print("hoi im tem!!");
		}
	}

	public double getDesPos() {
		return desPos;
	}

	public void setDesPos(double desPos) {
		this.desPos = desPos;
	}
}
