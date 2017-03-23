package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.network.Client;
import org.usfirst.frc.team5427.robot.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChangeIP extends Command {

	protected void Initialize(){
		Client newIP = new Client(SmartDashboard.getString("DriverStationIP", Config.DRIVER_STATION_IP),Config.DRIVER_STATION_PORT,Robot.swip);
		Robot.client.stop();
		Robot.client = newIP;
		Robot.client.start();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
