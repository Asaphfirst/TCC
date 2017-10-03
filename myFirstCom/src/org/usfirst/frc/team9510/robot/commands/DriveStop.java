package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStop extends Command {

	public DriveStop() {
		// super(1);
		requires(Robot.drivetrain);
	}

	protected void initialize() { // Get everything in a safe starting state.
		Robot.drivetrain.reset();

	}

	protected void execute() {
		Robot.drivetrain.drive.setLeftRightMotorOutputs(0.0, 0.0);
		
	}

	protected void end() {

		Robot.drivetrain.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
}
}
