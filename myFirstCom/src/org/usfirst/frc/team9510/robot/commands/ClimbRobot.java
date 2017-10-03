package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

public class ClimbRobot extends Command {
	public double speed;
	public ClimbRobot(double sp) {
		requires(Robot.Climber1);
		speed = sp;
	}

	@Override
	protected void execute() {
		Robot.Climber1.climb(speed);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void end() {
		Robot.Climber1.stopClimb();
	}

}
