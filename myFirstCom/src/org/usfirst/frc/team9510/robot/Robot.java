
package org.usfirst.frc.team9510.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team9510.robot.commands.AutoLeftLift;
import org.usfirst.frc.team9510.robot.commands.AutoLeftLiftRed;
import org.usfirst.frc.team9510.robot.commands.AutoMiddleLift;
import org.usfirst.frc.team9510.robot.commands.AutoRightLift;
import org.usfirst.frc.team9510.robot.commands.AutoRightLiftRed;
import org.usfirst.frc.team9510.robot.subsystems.Climber;
import org.usfirst.frc.team9510.robot.subsystems.Collector;
import org.usfirst.frc.team9510.robot.subsystems.DriveTrain;
import org.usfirst.frc.team9510.robot.subsystems.ExampleSubsystem;
import java.io.*; // Test UDP
import java.net.*;// Test UDP

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain drivetrain;
	public static Climber Climber1;
	public static ExampleSubsystem example;
	public static Collector collector;
	
//	public static OI oi;
//	public static DriveTrain drivetrain = new DriveTrain();
//	public static Climber Climber1 = new Climber();
//	public static ExampleSubsystem example = new ExampleSubsystem();
//	public static Collector collector = new Collector();
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		
		drivetrain = new DriveTrain();
		Climber1 = new Climber();
		collector = new Collector();
		oi = new OI();
		
		// BLUE ALLIANCE AUTO
		chooser.addDefault("Default Auto", new AutoMiddleLift());
		chooser.addObject("Left Lift BLUE", new AutoLeftLift());
		chooser.addObject("Middle Lift BLUE/RED", new AutoMiddleLift());
		chooser.addObject("Right Lift BLUE", new AutoRightLift());
		
		//RED ALLIANCE AUTO
		chooser.addObject("Left Lift RED", new AutoLeftLiftRed());
		chooser.addObject("Right Lift RED", new AutoRightLiftRed());
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(drivetrain);

	}

//	@Override
//	public void disabledInit() {
//		Scheduler.getInstance().removeAll(); // Teste bug auto
//	}

//	@Override
//	public void disabledPeriodic() {
//		// Scheduler.getInstance().run(); testando para resolver problema do
//		// autonomo
//		Scheduler.getInstance().removeAll();
//	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll(); // testando mais uma vez para
												// resolver o problema do
												// autonomo
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();

		drivetrain.reset();
	}

	@Override
	public void autonomousPeriodic() { // This function is called periodically
										// during autonomous
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
		drivetrain.reset();
	}

	@Override
	public void teleopPeriodic() { // This function is called periodically //
									// during operator control
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void log() {
		drivetrain.log();

	}
}

// String autoSelected = SmartDashboard.getString("Auto", "Default Auto");
// System.out.println(autoSelected);
//
// switch (autoSelected) {
// case "1":
// autonomousCommand = new AutoLeftLift();
// System.out.println("Left Lift");
// break;
// case "2":
// autonomousCommand = new AutoMiddleLift();
// System.out.println("Middle Lift");
// break;
// case "3":
// autonomousCommand = new AutoRightLift();
// System.out.println("Right Lift");
// break;
//
// case "Default Auto":
// default:
// autonomousCommand = new AutoMiddleLift();
// System.out.println("Entrou no Default");
// break;
// }
