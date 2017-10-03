package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTurn extends Command {

	public double ang;
	public boolean status2;

	public DriveTurn(double angle) {
		//super(1);
		requires(Robot.drivetrain);
		ang = angle;
	}

	protected void initialize() { // Get everything in a safe starting state.
		status2 = false;
		Robot.drivetrain.reset();

	}

	protected void execute() {
		if (ang > 0) {
			if (ang > Robot.drivetrain.getAngle()) {
				if ((ang - Robot.drivetrain.getAngle()) < (ang * 0.5))
					Robot.drivetrain.drive.arcadeDrive(0, -0.6);
				else
					Robot.drivetrain.drive.arcadeDrive(0, -0.8);
			} else {
				Robot.drivetrain.stop();

			}
		} else if (ang < 0) {
			if (ang < Robot.drivetrain.getAngle()) {
				if ((ang + Math.abs(Robot.drivetrain.getAngle())) > (ang * 0.5))
					Robot.drivetrain.drive.arcadeDrive(0, 0.6);
				else
					Robot.drivetrain.drive.arcadeDrive(0, 0.8);
			} else {
				Robot.drivetrain.stop();
				status2 = true;

			}

		}
		// super.execute(); /// testando problema de reiniciar o autonomous
	}

	protected void end() {

		Robot.drivetrain.stop();
	}

	@Override
	protected boolean isFinished() {
		return ((ang > 0) && (ang <= Robot.drivetrain.getAngle()))
				|| ((ang < 0) && (ang >= Robot.drivetrain.getAngle()));
	}
}
