// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Commands.ScoreAndLeave;

public class AutoChooser extends SubsystemBase {
/* Declare subsystem dependencies */
private SendableChooser<String> AutoSelector;
public ShuffleboardTab Autos;

Command m_ScoreAndLeave = new ScoreAndLeave();

public static final String BackwardsAuto = "go backwards";
public static final String ScoreAndLeave = "score speaker and leave";
public static final String ShootAuto = "score ad stay";

private String Selected;

public static final String TopShooterMotor = "the upper shooter motor";
public static final String BottomShooterMotor = "the lower shooter motor";


  public AutoChooser () {

    AutoSelector = new SendableChooser<String>();
    Autos = Shuffleboard.getTab("Auto ");

    AutoSelector.addOption("Score and leave", ScoreAndLeave);
    AutoSelector.setDefaultOption("Back up", BackwardsAuto);
    AutoSelector.addOption("score", ShootAuto);
    
    Autos.add("Auto selector",AutoSelector).withPosition(2, 0).withSize(6, 4);
  }
  public void addVariable(String name, double item){
    Autos.add(name, item).withPosition(0,0).withSize(1,1);
  }
  public String getSelectedOption() {
    Selected = AutoSelector.getSelected();

    return Selected;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
