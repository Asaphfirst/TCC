package org.usfirst.frc.team9510.robot.commands;

import org.usfirst.frc.team9510.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightLiftRed extends CommandGroup {
	public AutoRightLiftRed(){
		addSequential(new DriveStraight(RobotMap.pegstowall - (RobotMap.robotLength * 1.5)));
		addSequential(new DriveTurn(-RobotMap.angpeg));
		addSequential(new DriveStraight(0.5));
		addSequential(new TesteCommandGroup());
		
//		addSequential(new AutoDeliver());
//		addSequential(new AutoDriveBack());
	}
}
