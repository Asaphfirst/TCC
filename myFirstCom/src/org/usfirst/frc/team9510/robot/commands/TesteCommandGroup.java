package org.usfirst.frc.team9510.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TesteCommandGroup extends CommandGroup {

	public TesteCommandGroup(){
		addParallel(new AutoDeliver());
		addParallel(new AutoDriveBack());
	}
}
