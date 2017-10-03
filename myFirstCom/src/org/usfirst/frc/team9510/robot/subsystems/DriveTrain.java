package org.usfirst.frc.team9510.robot.subsystems;

import org.usfirst.frc.team9510.robot.OI;
import org.usfirst.frc.team9510.robot.Robot;
import org.usfirst.frc.team9510.robot.RobotMap;
import org.usfirst.frc.team9510.robot.commands.DriveRobot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	public CANTalon left = new CANTalon(1);
	public CANTalon right = new CANTalon(2);

	public RobotDrive drive = new RobotDrive(left, right);
	
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public DriveTrain() {
		super();

		if (Robot.isReal()) {

			left.configEncoderCodesPerRev(4096);
			right.configEncoderCodesPerRev(4096);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveRobot());
	}

	public void drive(double left, double right) {
		drive.arcadeDrive(left, right);

	}

	public void drive(XboxController stick) {
		drive(-OI.stick.getRawAxis(1)*0.5, -OI.stick.getRawAxis(4) * 0.5);
	}

	public void reset() {
		gyro.reset();
		left.setEncPosition(0);
		right.setEncPosition(0);

	}

	public double getLeftDistance() {
		double leftDist = (Robot.drivetrain.left.getEncPosition() * RobotMap.distPulse);
		return leftDist;
	}

	public double getRightDistance() {
		double RightDist = (Robot.drivetrain.right.getEncPosition() * RobotMap.distPulse);
		return RightDist;
	}
	
	public double getAngle(){
		double angle = gyro.getAngle();
		return angle;
	}
	

	public void log() {
		SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("Right distance", getRightDistance());
		SmartDashboard.putNumber("Left distance", getLeftDistance());
		SmartDashboard.putNumber("Right Encoder", right.getEncPosition());
		SmartDashboard.putNumber("Left Encoder", left.getEncPosition());
	}

	public void goForward() {
		drive.arcadeDrive(-0.5, 0.0);
		//drive.setLeftRightMotorOutputs(-0.5, 0);
	}

	public void goBackward() {
		drive.arcadeDrive(0.5, 0.0);
	}

	public void stop() {
		drive.arcadeDrive(0, 0);

	}

}
