// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5427.pidgyro;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static AnalogGyro driveTrainPIDAnalogGyro1;
    public static SpeedController driveTrainPIDFrontLeft;
    public static SpeedController driveTrainPIDBackLeft;
    public static SpeedController driveTrainPIDFrontRight;
    public static SpeedController driveTrainPIDBackRight;
    public static RobotDrive driveTrainPIDRobotDrive41;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainPIDAnalogGyro1 = new AnalogGyro(0);
        LiveWindow.addSensor("DriveTrainPID", "AnalogGyro 1", driveTrainPIDAnalogGyro1);
        driveTrainPIDAnalogGyro1.setSensitivity(0.007);
        driveTrainPIDFrontLeft = new Talon(0);
        LiveWindow.addActuator("DriveTrainPID", "FrontLeft", (Talon) driveTrainPIDFrontLeft);
        
        driveTrainPIDBackLeft = new Talon(1);
        LiveWindow.addActuator("DriveTrainPID", "BackLeft", (Talon) driveTrainPIDBackLeft);
        
        driveTrainPIDFrontRight = new Talon(2);
        LiveWindow.addActuator("DriveTrainPID", "FrontRight", (Talon) driveTrainPIDFrontRight);
        
        driveTrainPIDBackRight = new Talon(3);
        LiveWindow.addActuator("DriveTrainPID", "BackRight", (Talon) driveTrainPIDBackRight);
        
        driveTrainPIDRobotDrive41 = new RobotDrive(driveTrainPIDFrontLeft, driveTrainPIDBackLeft,
              driveTrainPIDFrontRight, driveTrainPIDBackRight);
        
        driveTrainPIDRobotDrive41.setSafetyEnabled(true);
        driveTrainPIDRobotDrive41.setExpiration(0.1);
        driveTrainPIDRobotDrive41.setSensitivity(0.5);
        driveTrainPIDRobotDrive41.setMaxOutput(1.0);
        driveTrainPIDRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        driveTrainPIDRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainPIDRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainPIDRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
