// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

public class BackwardsAuto extends Command {
  /** Creates a new BackwardsAuto. */
  // Declare subsytem Dependencies
  CommandXboxController m_operatorController;
  DriveBase m_DriveBase;
  Shooter m_Shooter;
  Encoder m_rightEncoder;
  Encoder m_leftEncoder;
  TalonFX rightFrontMotor;
  TalonFX rightBackMotor;
  TalonFX leftFrontMotor;
  TalonFX leftBackMotor;

  public BackwardsAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*  Reset Encoder Counts */
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  double setpoint = -5;

  final double kP = 0.05;
  final double kI = 0.05;
  final double kD = 0.05;

  PIDController pid = new PIDController(kP, kI, kD);

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Output Motors
    rightFrontMotor.set(pid.calculate(m_rightEncoder.getDistance(), setpoint));
    rightFrontMotor.set(pid.calculate(m_rightEncoder.getDistance(), setpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pid.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
