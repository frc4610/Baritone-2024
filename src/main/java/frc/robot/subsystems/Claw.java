// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DifferentialFollower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClawConstants;



public class Claw extends SubsystemBase {

  // Declare claw motors
  public TalonFX clawMotor= new TalonFX(Constants.DeviceIds.kClawMotorId);

  /** Creates a new Claw. */
  public Claw() {

  /* Motor Configuration */

    // set claw motor inversion to true
    clawMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  // Shoot with claw command
  public void scoreAmp() {
 clawMotor.set(ClawConstants.kClawIntakeSpeed);};
  
  // Pick up with claw command
  public void intakeClaw() {
  clawMotor.set(ClawConstants.kClawOuttakeSpeed);};
  
  // No claw buttons pressed command
  public void idleClaw() {
  clawMotor.set(ClawConstants.kClawIdleSpeed);};
}
