// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  /** Creates a new Climber. */

  //  Declare operator controller
  CommandXboxController m_operatorController = new CommandXboxController(Constants.OperatorConstants.kOperatorControllerPort);

  //  Declare Motors
  TalonFX climbMotor = new TalonFX(Constants.DeviceIds.kClimbMotorId);
  

  public Climber() {
  /*  Motor Configurations */

  //  Motor Safety
  climbMotor.setSafetyEnabled(false);

  /*  Motor Control */
  //  Set Motors Inverted
  climbMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void raiseClimber(){
    //  set motors to climbing up constant
  climbMotor.set(Constants.ClimbConstants.kClimbUpSpeed);

  }
  public void lowerClimber(){
    // set motors to climbing down constant
    climbMotor.set(Constants.ClimbConstants.kClimbDownSpeed);

  }
  public void stopClimb(){
    //  Set motors to zero through constants
  climbMotor.set(Constants.ClimbConstants.kStopClimb);
   }
}
