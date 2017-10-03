package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;


import edu.wpi.first.wpilibj.command.TimedCommand;

public class AutoDriveBack extends TimedCommand {
	public AutoDriveBack(){
		super(1);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void execute() {
		Robot.drivetrain.drive.setLeftRightMotorOutputs(-0.3, -0.3);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.drive.setLeftRightMotorOutputs(0.0, 0.0);
	}
}