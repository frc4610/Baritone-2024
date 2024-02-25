// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.DifferentialFollower;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;

import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class DriveBase extends SubsystemBase {

  /* Device Declaration */

  // Declare Controller
  CommandXboxController m_driverControl = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

  // Declare Motors
  TalonFX rightFrontMotor = new TalonFX(Constants.DeviceIds.kFrontRightId);
  TalonFX rightBackMotor = new TalonFX(Constants.DeviceIds.kBackRightId);
  TalonFX leftFrontMotor = new TalonFX(Constants.DeviceIds.kFrontLeftId);
  TalonFX leftBackMotor = new TalonFX(Constants.DeviceIds.kBackLeftId);

  // Delcare Gyro
  Pigeon2 m_gyro = new Pigeon2(Constants.DeviceIds.kGyroId);
  
  // Declare Differential Drive
  DifferentialDrive m_drive;
//  gear ratio 33.5

public StatusSignal<Double> rightVelocity(){
  // get motor positions based in meters
  
  return rightFrontMotor.getVelocity();
}
public StatusSignal<Double> leftVelocity(){
 return leftFrontMotor.getVelocity();
}
public StatusSignal<Double> rightRotation(){
    return rightFrontMotor.getPosition();
}
public StatusSignal<Double> leftRotation(){
    return leftFrontMotor.getPosition();
}
public StatusSignal<Double> rightTickCount(){
  return rightFrontMotor.();
}

  //  Odometry
   private final DifferentialDriveOdometry m_Odometry;

   // Mutable holder for unit-safe voltage values, persisted to avoid reallocation.
  private final MutableMeasure<Voltage> m_appliedVoltage = mutable(Volts.of(0));
  // Mutable holder for unit-safe linear distance values, persisted to avoid reallocation.
  private final MutableMeasure<Distance> m_distance = mutable(Meters.of(0));
  // Mutable holder for unit-safe linear velocity values, persisted to avoid reallocation.
  private final MutableMeasure<Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));

  // Create a new SysId routine for characterizing the drive.
  private final SysIdRoutine m_sysIdRoutine =
      new SysIdRoutine(
          // Empty config defaults to 1 volt/second ramp rate and 7 volt step voltage.
          new SysIdRoutine.Config(),
          new SysIdRoutine.Mechanism(
              // Tell SysId how to plumb the driving voltage to the motors.
              (Measure<Voltage> volts) -> {
                leftFrontMotor.setVoltage(volts.in(Volts));
                rightFrontMotor.setVoltage(volts.in(Volts));
              },
              // Tell SysId how to record a frame of data for each motor on the mechanism being
              // characterized.
              log -> {
                // Record a frame for the left motors.  Since these share an encoder, we consider
                // the entire group to be one motor.
                log.motor("drive-left")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                              leftFrontMotor.get() * RobotController.getBatteryVoltage(), Volts))
                    .linearPosition(m_distance.mut_replace(getPosition().getDistance(), Meters))
                    .linearVelocity(
                        m_velocity.mut_replace(rightFrontMotor.getRate(), MetersPerSecond));
                // Record a frame for the right motors.  Since these share an encoder, we consider
                // the entire group to be one motor.
                log.motor("drive-right")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                            rightFrontDrive.get() * RobotController.getBatteryVoltage(), Volts))
                    .linearPosition(m_distance.mut_replace(m_rightEncoder.getDistance(), Meters))
                    .linearVelocity(
                        m_velocity.mut_replace(m_rightEncoder.getRate(), MetersPerSecond));
              },
              // Tell SysId to make generated commands require this subsystem, suffix test state in
              // WPILog with this subsystem's name ("drive")
              this));

  
  private final ShuffleboardTab m_EncoderTab;

  // 

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

    //  Sends Encoder data to Shufleboard
    m_EncoderTab = Shuffleboard.getTab("Encoders");

    m_EncoderTab.addDouble("Right Encoder Count", () -> /*Insert Encoder */.getDistance());
    m_EncoderTab.addDouble("Left Encoder Count", () -> /*Insert Encoder */.getDistance());
    m_EncoderTab.addDouble("Gyro Heading", () -> m_gryo.getAngle());



    m_drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor); // For motor testing

      //converts 
  m_leftEncoder.setDistancePerPulse(Constants.DrivetrainConstants.kLinearDistanceConversionFactor);
  m_rightEncoder.setDistancePerPulse(-Constants.DrivetrainConstants.kLinearDistanceConversionFactor);

  resetEncoders();
  m_gryo.reset();

  m_Odometry = new DifferentialDriveOdometry(m_gryo.getRotation2d(),
   m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }

  public void drive(){
    m_drive.curvatureDrive(
      MathUtil.applyDeadband(m_driverControl.getLeftY()*-1, 0.05),
      MathUtil.applyDeadband(m_driverControl.getRightX(), 0.05), 
      m_driverControl.getHID().getRightBumper());

    // m_drive.curvatureDrive(m_driverControl.getLeftY()*-1, m_driverControl.getRightX(), m_driverControl.getHID().getRightBumper()); Old Working Code
  }

  public void resetEncoders(){
    /*Insert Encoder */.reset();
    /*Insert Encoder */.reset();
  }

  public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.quasistatic(direction);
  }

  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.dynamic(direction);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    drive();
    m_Odometry.update
    (m_gyro.getRotation2d(),
     m_leftDrive.getDistance(), 
     m_rightDrive.getDistance());
  }

  }
