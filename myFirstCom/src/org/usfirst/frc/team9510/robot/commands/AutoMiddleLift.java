package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleLift extends CommandGroup {
	public AutoMiddleLift(){
		addSequential(new DriveStraight(RobotMap.middlepegtowall - RobotMap.robotLength));
		addSequential(new TesteCommandGroup());
		
		//addSequential(new AutoDeliver());
		
	}
}

//addSequential(new DriveStraight(0.5));
////addSequential(new TesteCommandGroup());//meia boca.
//addSequential(new AutoDeliver());
//addSequential(new AutoDriveBack());