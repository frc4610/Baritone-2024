//Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */


public class SysIdRoutineBot {
  //  The Robot's subsystems and commands are defined here

  /* ---Subsystems--- */
  private final DriveBase m_driveBase = new DriveBase();
  private final Shooter m_Shooter = new Shooter();
  private final Climber m_Climber = new Climber();
  private final Claw m_claw = new Claw();

  /* ---Controllers--- */

  // Driver Controller
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  // Operator Controller
  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);
      
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public SysIdRoutineBot() {

    // Configure the trigger bindings
  configureBindings();
 }

  /**
   * Use this method to define bindings between conditions and commands. These are useful for
   * automating robot behaviors based on button and sensor input.
   *
   * <p>Should be called during {@link Robot#robotInit()}.
   *
   * <p>Event binding methods are available on the {@link Trigger} class.
   */
  public void configureBindings() {
      /*  ---Operator bindings--- */
      /* ---Shooter Bindings--- */

      // Trigger scoreSpeaker method when right bumper is pressed, resets to stopShooter when released
      m_operatorController.rightBumper().whileTrue(Commands.startEnd(
        () -> {m_Shooter.scoreSpeaker();},
        () -> {m_Shooter.stopShooter();}, 
        m_Shooter));
    // Trigger intakeNote method when left bumper is pressed, resets to stopShooter when released
      m_operatorController.leftBumper().whileTrue(Commands.startEnd(
        () -> {m_Shooter.intakeNote();},
        () -> {m_Shooter.stopShooter();},
        m_Shooter ));
              /*  ---Climber Bindings--- */

      // Trigger raiseclimber method when 'A' is pressed, resets to stopClimber when released
      m_operatorController.a().whileTrue(Commands.startEnd(
        () -> {m_Climber.raiseClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));
      // Trigger lowerClimber method when 'Y' is pressed, resets to stopClimber when released
      m_operatorController.y().whileTrue(Commands.startEnd(
        () -> {m_Climber.lowerClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));

      /* ---Claw Bindings--- */

      // Trigger scoreAmp command 'B' is pressed, reset to idleClaw when released
     m_operatorController.b().whileTrue(Commands.startEnd(
      () -> {m_claw.scoreAmp();},
      () -> {m_claw.idleClaw();}, 
      m_claw));
      // Trigger intakeClaw command 'X' is pressed, reset to idleClaw when released
     m_operatorController.x().whileTrue(Commands.startEnd(
      () -> {m_claw.intakeClaw();},
      () -> {m_claw.idleClaw();}, 
      m_claw));

    // Bind full set of SysId routine tests to buttons; a complete routine should run each of these
    // once.
    m_driverController.a().whileTrue(
      m_driveBase.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    m_driverController.b().whileTrue(
      m_driveBase.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    m_driverController.x().whileTrue(
      m_driveBase.sysIdDynamic(SysIdRoutine.Direction.kForward));
    m_driverController.y().whileTrue(
      m_driveBase.sysIdDynamic(SysIdRoutine.Direction.kReverse));
  }

  /**
   * Use this to define the command that runs during autonomous.
   *
   * <p>Scheduled during {@link Robot#autonomousInit()}.
   */
  public Command getAutonomousCommand() {
    // Do nothing
    return m_driveBase.run(() -> {});
  }
}