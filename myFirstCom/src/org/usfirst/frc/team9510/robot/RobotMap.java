package org.usfirst.frc.team9510.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	// Note: Measurements are in metres
		// Wheel Measurements
		public static double wheelDiameter = 0.1524, wheelCirumference = wheelDiameter*Math.PI; 
		
		public static double distPulse = wheelCirumference/4095;
		// Arena Measurements
		public static double baseLineDist = 2.84 , middlepegtowall = 2.54, pegstowall = 3.17 , angpeg = 50; 
		//Robot Measurements
		public static double robotLength = 0.80, robotWidth = 0;
		
		
		
}
