package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveRobot extends Command {

	public DriveRobot() {
		requires(Robot.drivetrain);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Robot.drivetrain.drive.arcadeDrive(OI.stick.getRawAxis(1), -OI.stick.getRawAxis(4) * 0.8);
		Robot.drivetrain.drive(Robot.oi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}
	

}
