// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public final class DeviceIds {

    /* Drive Base Subsystem Device Ids */

    // Drive Motor Ids
    public static final int kFrontRightId = 10;
    public static final int kBackRightId = 11;
    public static final int kFrontLeftId = 12;
    public static final int kBackLeftId = 13;

    // Encoder
    public static final int kRightEncoder_A = 15;
    public static final int kRightEncoder_B= 16;
    public static final int kLeftEncoder_A = 17;
    public static final int kLeftEncoder_B = 18;
    // Gyro
    public static final int kGyroId = 14;

    /* Shooter Subsystem Device Ids */
    
    // Shooter Motor Ids
    public static final int kTopMotorId = 20;
    public static final int kBottomMotorId = 21;

    /* Claw Subsystem Device Ids */

    // Claw Motor Ids
    public static final int kClawMotorId = 30;

    /* Climber Subsystem Device Ids */

    // Climber Motor Ids
    public static final int kClimbMotorId = 40;
  }

  public final class ShooterConstants {
    /*  Shooter Constants */

    //  Edit based on robot funtionality or driveteam preference
    //  Edit at Mentors Discretion

    // Stop Motors 
    public static final int kStopMotors = 0;

   //  Set Shoot Constant
    public static final double kShootSpeaker = -0.85;

    //  Set intake Constant
    public static final double kShooterIntake = 0.5;
  }
  
  public final class ClimbConstants {
    /*  Climber Constants */
    //  Edit based on climb mechanisms functionality

    //  Set Climbing Upwards Constant
    public static final double kClimbUpSpeed = 0.5;

    //  Set Climbing Down Constant
    public static final double kClimbDownSpeed = -0.5;

    public static final int kStopClimb = 0;

  }

  public final class ClawConstants{

    /*Claw motor speeds*/

    // Claw motor forward speed
    public static final double kClawIntakeSpeed = 0.75;

    // Claw motor backward speed
    public static final double kClawOuttakeSpeed = -0.75;

    // Claw idle speed 
    public static final double kClawIdleSpeed = -0.25;
  }
  public final class DriveBaseConstants {

    public static final double driveKP = 0.05; 
    public static final double driveKI = 0.0;
    public static final double driveKD = 0.0;
    public static final double driveKF = 0.0;


    public static final double kWheelDiameterMeters = 6 * Math.PI;

    public static final double kDriveTick2Feet = 1.0 / 2048 * 6 * Math.PI / 12;
  }
}
