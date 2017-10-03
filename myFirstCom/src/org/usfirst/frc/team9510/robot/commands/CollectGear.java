package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class CollectGear extends Command {

	public CollectGear() {
		requires(Robot.collector);
	}

	protected void execute() {
		Robot.collector.collect(0.3, Value.kReverse);
		// super.execute();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
