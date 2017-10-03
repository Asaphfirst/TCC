package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class AutoDeliver extends TimedCommand {

	public AutoDeliver() {
		super(2);
		requires(Robot.collector);
	}

	// Called just before this Command runs the first time
	@Override
	protected void execute() {
		Robot.collector.deliver(0.3, Value.kReverse);

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.collector.stand(0.0, Value.kForward);
	}
}
