// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Commands.BackwardsAuto;
import frc.robot.Commands.ScoreAndLeave;
import frc.robot.Commands.ShootAuto;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.AutoChooser;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /* ---Subsystems--- */
  private final DriveBase m_driveBase = new DriveBase();
  private final Shooter m_Shooter = new Shooter();
  private final Climber m_Climber = new Climber();
  private final Claw m_claw = new Claw();

  /* ---Controllers--- */

  /*  Experimental Flight Joystick Controls
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);
  */
  // Controllers
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  // Operator Controller
  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
  configureBindings();
 }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

      /* ---Shooter Bindings--- */

      // Trigger scoreSpeaker method when right bumper is pressed, resets to stopShooter when released
      m_operatorController.rightBumper().toggleOnTrue(Commands.startEnd(
        () -> {m_Shooter.scoreSpeaker();},
        () -> {m_Shooter.stopShooter();}, 
        m_Shooter));
    // Trigger intakeNote method when left bumper is pressed, resets to stopShooter when released
      m_operatorController.leftBumper().toggleOnTrue(Commands.startEnd(
        () -> {m_Shooter.intakeNote();},
        () -> {m_Shooter.stopShooter();},
        m_Shooter ));

      /*  ---Climber Bindings--- */

      // Trigger raiseclimber method when 'A' is pressed, resets to stopClimber when released
      m_operatorController.a().toggleOnTrue(Commands.startEnd(
        () -> {m_Climber.raiseClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));
      // Trigger lowerClimber method when 'Y' is pressed, resets to stopClimber when released
      m_operatorController.y().toggleOnTrue(Commands.startEnd(
        () -> {m_Climber.lowerClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));

      /* ---Claw Bindings--- */

      // Trigger scoreAmp command 'B' is pressed, reset to idleClaw when released
     m_operatorController.b().toggleOnTrue(Commands.startEnd(
      () -> {m_claw.scoreAmp();},
      () -> {m_claw.idleClaw();}, 
      m_claw));
      // Trigger intakeClaw command 'X' is pressed, reset to idleClaw when released
     m_operatorController.x().toggleOnTrue(Commands.startEnd(
      () -> {m_claw.intakeClaw();},
      () -> {m_claw.idleClaw();}, 
      m_claw));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous

    if(AutoChooser.ScoreAndLeave = true){
    
    return null;
    } 
    else if (AutoChooser.ShootAuto = true){

      return null;
    } 
    else (AutoChooser.BackwardsAuto = true){
      return null;
    }

    
  
   
  }
}
