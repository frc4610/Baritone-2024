// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
  }

  public final class DeviceIds {

    /* Drive Base Subsystem Device Ids */

    // Drive Motor Ids
    public static final int kFrontRightId = 10;
    public static final int kBackRightId = 11;
    public static final int kFrontLeftId = 12;
    public static final int kBackLeftId = 13;

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
}
