package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class stores the Cameras and changes the current camera that is projecting onto the dashboard 
 */
public class RobotCameras extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public int currentCamera=0;
	public  UsbCamera usbCamera0;
	public  UsbCamera usbCamera1;
	public  AxisCamera axisCamera;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
	}
	public RobotCameras(UsbCamera usbCamera0,UsbCamera usbCamera1,AxisCamera axisCamera)
	{
		this.usbCamera0=usbCamera0;
        this.usbCamera1=usbCamera1;
        this.axisCamera=axisCamera;
	}
	public VideoSource getCurrentCamera()
	{
		if(currentCamera==0)
			return usbCamera0;
		else if(currentCamera==1)
			return usbCamera1;
		else if(currentCamera==2)
			return axisCamera;
		else 
			return null;
	}
	public void setCurrentCamera(int currentCamera)
	{
		this.currentCamera=currentCamera;
	}
	public void changeCamera()
	{
		Robot.server.removeCamera(getCurrentCamera().toString());
		if(currentCamera==2)
			currentCamera=-1;
		
		currentCamera++;
		
		Robot.server.addCamera(getCurrentCamera());
		Robot.server.startAutomaticCapture(getCurrentCamera());
	}
}
