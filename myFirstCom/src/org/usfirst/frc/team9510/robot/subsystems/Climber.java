package org.usfirst.frc.team9510.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	public VictorSP climberLeft  = new VictorSP(0);
	public VictorSP climberRight  = new VictorSP(1);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void climb(double speed){
		climberLeft.set(speed);
		climberRight.set(speed);
	}
	public void stopClimb(){
		climberLeft.set(0.0);
		climberRight.set(0.0);
	}
}
