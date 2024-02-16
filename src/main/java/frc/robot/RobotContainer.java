// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // Declare robot subsystems here
  private final DriveBase m_driveBase = new DriveBase();
  private final Shooter m_Shooter = new Shooter();
  private final Climber m_Climber = new Climber();


  // Declare driver controller 
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    /*  new method */
    /*  Sets default command as the default command */
    m_Shooter.setDefaultCommand(new Shooter().defaultCommand());
    m_Climber.setDefaultCommand(new Climber().defaultCommand());
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
    /* Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    * new Trigger(m_exampleSubsystem::exampleCondition)
    *    .onTrue(new ExampleCommand(m_exampleSubsystem));

    * Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    * cancelling on release.
    *m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    */
      /*  sets shooter commands based on a bool */
      m_operatorController.rightBumper().whileTrue(m_Shooter.scoreSpeaker());
      m_operatorController.leftBumper().whileTrue(m_Shooter.intakeNote());
      /*  sets climber commands based of a bool */
      m_operatorController.a().whileTrue(m_Climber.lowerClimber());
      m_operatorController.y().whileTrue(m_Climber.raiseClimber());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
