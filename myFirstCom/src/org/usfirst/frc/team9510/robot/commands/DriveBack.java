package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveBack extends Command {

	public double dist;
	public boolean status;

	public DriveBack(double distance) {
		//super(1);
		requires(Robot.drivetrain);
		dist = distance;
	}

	protected void initialize() { // Get everything in a safe starting state.
		status = false;
		Robot.drivetrain.reset();
		System.out.println("inicializou o Straight");
	}

	protected void execute() {
		double error = Robot.drivetrain.getAngle();
		double parameter = 0.03;

		if (dist > Math.abs(Robot.drivetrain.getLeftDistance()) && dist > Math.abs(Robot.drivetrain.getRightDistance()))
			Robot.drivetrain.drive.setLeftRightMotorOutputs(-0.3 - (error * parameter), -0.3 + (error * parameter));
		else {
			Robot.drivetrain.stop();
			status = true;
		}

	}

	protected void end() {

		Robot.drivetrain.stop();
	}

	@Override
	protected boolean isFinished() {
		return status;//(dist <= Math.abs(Robot.drivetrain.getLeftDistance()) && dist <= Math.abs(Robot.drivetrain.getRightDistance()));
	}

}
