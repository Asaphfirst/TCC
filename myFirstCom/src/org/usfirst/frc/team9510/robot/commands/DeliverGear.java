package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class DeliverGear extends Command {

	public DeliverGear() {
		requires(Robot.collector);
	}

	@Override
	protected void execute() {
		Robot.collector.deliver(0.3, Value.kReverse);
		//super.execute();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
