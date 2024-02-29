// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveBase;

public class AutoReverse extends Command {
  double m_setPoint;
  DriveBase m_driveBase;

  /** Creates a new AutoReverse. */
  public AutoReverse(DriveBase driveBase, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_driveBase = driveBase;
    this.m_setPoint = setpoint;
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  m_driveBase.resetEncoders();
  }

  final double kP = .05;
  final double kI = .05;
  final double kD = .05;

  PIDController pid = new PIDController(kP, kI, kD);

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  // Output to motors 
  m_driveBase.tankDrive(pid.calculate(m_driveBase.getLeftEncoder(), m_setPoint),
 (pid.calculate(m_driveBase.getRightEncoder(), m_setPoint)));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
