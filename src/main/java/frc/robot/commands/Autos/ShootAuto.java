// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

public class ShootAuto extends Command {
  /** Creates a new ShootAuto. */
  // Declare robot dependencies
  CommandXboxController m_operatorController;
  DriveBase m_DriveBase;
  Shooter m_Shooter;
  
  double m_setpoint;

  public ShootAuto() {
    // Use addRequirements() here to declare subsystem dependencies. 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
        /*  Reset Encoder Counts */
      m_DriveBase.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

     m_Shooter.scoreSpeaker();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
