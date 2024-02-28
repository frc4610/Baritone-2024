// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DifferentialFollower;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {

  /* Device Declaration */

  // Declare Controller
  CommandXboxController m_driverControl = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

  // Declare Motors
  TalonFX rightFrontMotor = new TalonFX(Constants.DeviceIds.kFrontRightId);
  TalonFX rightBackMotor = new TalonFX(Constants.DeviceIds.kBackRightId);
  TalonFX leftFrontMotor = new TalonFX(Constants.DeviceIds.kFrontLeftId);
  TalonFX leftBackMotor = new TalonFX(Constants.DeviceIds.kBackLeftId);

  // Declare Encoders
  private static final Encoder m_rightEncoder = new Encoder(Constants.DeviceIds.kRightEncoder_A, Constants.DeviceIds.kRightEncoder_B); //right side
  private static final Encoder m_leftEncoder = new Encoder(Constants.DeviceIds.kLeftEncoder_A, Constants.DeviceIds.kLeftEncoder_B); //left side

  // Delcare Gyro
  Pigeon2 m_gyro = new Pigeon2(Constants.DeviceIds.kGyroId);

  // Declare Odometry
  DifferentialDriveOdometry m_Odometry;
  
  // Declare Differential Drive
  DifferentialDrive m_drive;

  /** Creates a new DriveBase. */
  public DriveBase() {
    
    /* Motor Configuration */

    // Motor Safety
    rightFrontMotor.setSafetyEnabled(false);
    rightBackMotor.setSafetyEnabled(false);
    leftFrontMotor.setSafetyEnabled(false);
    leftBackMotor.setSafetyEnabled(false);

    // Motor Control
    rightFrontMotor.setInverted(false);
    leftFrontMotor.setInverted(true);

    rightBackMotor.setControl(new Follower(Constants.DeviceIds.kFrontRightId, false));
    leftBackMotor.setControl(new Follower(Constants.DeviceIds.kFrontLeftId, false));

    // Encoder config 
    m_rightEncoder.setDistancePerPulse(Units.inchesToMeters(6 * Math.PI));

    // m_drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor); Original Code

    m_drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor); // For motor testing
  }

  public void drive(){
    m_drive.curvatureDrive(
      MathUtil.applyDeadband(m_driverControl.getLeftY()*-1, 0.05),
      MathUtil.applyDeadband(m_driverControl.getRightX(), 0.05), 
      m_driverControl.getHID().getRightBumper());

    // m_drive.curvatureDrive(m_driverControl.getLeftY()*-1, m_driverControl.getRightX(), m_driverControl.getHID().getRightBumper()); Old Working Code
  }

  public void resetEncoders(){
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    drive();
  m_Odometry.update(m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }

  public Command exampleCommand(){
    
    return this.runOnce(() -> { /* Command Logic */});
  }
}
