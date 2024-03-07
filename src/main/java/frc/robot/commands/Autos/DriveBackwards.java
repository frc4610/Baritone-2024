// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveBase;

public class DriveBackwards extends Command {
  /** Creates a new DriveBackwards. */
    private final DriveBase m_drive;
    private final double m_distance;
    private final double m_speed;
  public DriveBackwards(double inches, double speed, DriveBase drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_distance = inches;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }
  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.arcadeDrive(m_speed, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getAverageEncoderDistance()) >= m_distance;
  }
}
