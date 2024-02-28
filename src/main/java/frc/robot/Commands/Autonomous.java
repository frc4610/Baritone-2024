// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.List;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.DriveBaseConstants.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

public class Autonomous extends Command {
  /*  Call robot subsystems */
  CommandXboxController m_operatorController;
  DriveBase m_DriveBase;
  Shooter m_Shooter;
  Encoder m_rightEncoder;
  Encoder m_leftEncoder;
  TalonFX rightFrontMotor;
  TalonFX rightBackMotor;
  TalonFX leftFrontMotor;
  TalonFX leftBackMotor;
   



  public Autonomous() {
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  m_leftEncoder.reset();
  m_rightEncoder.reset();
  }
  double setpoint = 0;

  final double kP = 0;
  final double kI = 0;
  final double kD = 0;

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void autoInit(){
    {
  if (m_operatorController.povDown()){
    double setpoint = 10;
  }else if (m_operatorController.povUp()){
    double setpoint = 0;
  }
  // get calculations
  double error = setpoint - sensorPosition;
  double sensorPosition = m_rightEncoder.get() * kDriveTick2Feet;

  double outputSpeed = kP * error;

  // Output to motors 
  rightFrontMotor.set(outputSpeed);
  rightBackMotor.set(outputSpeed);
  leftFrontMotor.set(outputSpeed);
  leftBackMotor.set(outputSpeed);
  }
  @Override
  public void execute() {

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
}