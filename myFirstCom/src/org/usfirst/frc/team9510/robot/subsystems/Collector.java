package org.usfirst.frc.team9510.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem {

	Spark CollectorMotor = new Spark(2);
	DoubleSolenoid CollectorSol = new DoubleSolenoid(0, 1);

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void collect(double speed, Value val) {
		CollectorSol.set(val);
		CollectorMotor.set(speed);
	}

	public void deliver(double speed, Value val) {
		CollectorSol.set(val);
		CollectorMotor.set(-speed);

	}

	public void stand(double speed, Value val) {
		CollectorSol.set(val);
		CollectorMotor.set(speed);
	}
}
