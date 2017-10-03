package org.usfirst.frc.team9510.robot;

import org.usfirst.frc.team9510.robot.commands.ClimbRobot;
import org.usfirst.frc.team9510.robot.commands.CollectGear;
import org.usfirst.frc.team9510.robot.commands.DeliverGear;
import org.usfirst.frc.team9510.robot.commands.StandGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final XboxController stick = new XboxController(0);
	//public static final XboxController stick2 = new XboxController(1);
	public static final Joystick stick2 = new Joystick(1);
	public OI(){
	
	 Button operator_a = new JoystickButton(stick2, 11);// 
	 Button operator_b = new JoystickButton(stick2, 10);
	 Button operator_x = new JoystickButton(stick2, 9);
	 Button operator_y = new JoystickButton(stick2, 7);
	 Button operator_l1 = new JoystickButton(stick2,2);
	 Button operator_r1 = new JoystickButton(stick2,1);
	 
	 operator_a.whenPressed(new ClimbRobot(0.35));
	 operator_x.whenPressed(new ClimbRobot(0.75));
	 operator_y.whenPressed(new ClimbRobot(0.9));
	 operator_b.whenPressed(new ClimbRobot(0.0));
	 
	 operator_r1.whenPressed(new CollectGear());
	 operator_r1.whenReleased(new StandGear());
	 
	 operator_l1.whenPressed(new DeliverGear());
	 operator_l1.whenReleased(new StandGear());
	 
	}
	public XboxController getJoystick() {
		return stick;
	}
}
