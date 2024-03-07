// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreSpeakerAndLeave extends SequentialCommandGroup {

  DriveBase m_DriveBase;
  Shooter m_Shooter;

  /** Creates a new ScoreAndLeave. */
  public ScoreSpeakerAndLeave(DriveBase driveBase, Shooter shooter) {

    this.m_DriveBase = driveBase;
    this.m_Shooter = shooter;

    addCommands( 
    Commands.runOnce( () -> {m_DriveBase.resetEncoders(); m_DriveBase.resetGyro();}),
    Commands.runOnce( () -> {m_Shooter.scoreSpeaker();}, m_Shooter), 
    new WaitCommand(2),
    new DriveDistance(48, -0.5, m_DriveBase));
  }
}
