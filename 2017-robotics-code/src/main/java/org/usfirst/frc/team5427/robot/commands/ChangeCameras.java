//package org.usfirst.frc.team5427.robot.commands;
//
//
//import edu.wpi.first.wpilibj.command.Command;
//
//import org.usfirst.frc.team5427.robot.Robot;
//import org.usfirst.frc.team5427.robot.util.Log;
//
//public class ChangeCameras extends Command {
//	
//		boolean shouldFinish=false; 
//		
//		public ChangeCameras() {
//			// Use requires() here to declare subsystem dependencies
//			requires(Robot.roboCams);
//			
//		}
//
//		// Called just before this Command runs the first time
//		@Override
//		protected void initialize() {
//		}
//
//		// Called repeatedly when this Command is scheduled to run
//		@Override
//		protected void execute() {
//			Log.init("ChangeCamerass");
//			Robot.roboCams.changeCameras();
//			if(Robot.roboCams.currentCamera==0)
//			{
//				Robot.server.removeCamera("usbCam1");
//				Robot.server.addCamera(Robot.usbCam0);
//				Robot.server.putVideo("usbCam0", 200, 200);
//				Robot.server.startAutomaticCapture(Robot.usbCam0);
//			}
//			else if(Robot.roboCams.currentCamera==1)
//			{
//				Robot.server.removeCamera("usbCam0");
//				Robot.server.addCamera(Robot.usbCam1);
//				
//				Robot.server.putVideo("usbCam1", 200, 200);
//				Robot.server.startAutomaticCapture(Robot.usbCam1);
//				
//			}
//			
//			shouldFinish=true;
//		}
//			
//		// Make this return true when this Command no longer needs to run execute()
//		@Override
//		protected boolean isFinished() {
//			return shouldFinish;
//		}
//
//		// Called once after isFinished returns true
//		@Override
//		protected void end() {
//		}
//
//		// Called when another command which requires one or more of the same
//		// subsystems is scheduled to run
//		@Override
//		protected void interrupted() {
//		}
//
//
//}