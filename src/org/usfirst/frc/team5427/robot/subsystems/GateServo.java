package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.util.Config;
import org.usfirst.frc.team5427.robot.util.Log;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GateServo extends Subsystem {

	Servo servo;
	// Desired Position
	private double desPos;

	public GateServo(Servo servo) {
		this.servo = servo;
		servo.setSpeed(.3);
		changePos(Config.GATE_CLOSED);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void changePos(int position) {
		if (position == Config.GATE_CLOSED) {
//			position = Config.GATE_OPEN;
			servo.set(.8);
			setDesPos(.8);
			SmartDashboard.putNumber("Servo", .8);
		} 
		else if (position == Config.GATE_OPEN) {
//			position = Config.GATE_CLOSED;
			servo.set(.63);
			setDesPos(.63);
			SmartDashboard.putNumber("Servo", .63);
		}
	}

	public double getDesPos() {
		return desPos;
	}

	public void setDesPos(double desPos) {
		this.desPos = desPos;
	}
}