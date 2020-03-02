// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;
// import java.lang.System;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.networktables.NetworkTable;


// public class Limelight extends Subsystem {
//     public static NetworkTable table;
//     public static double tx;
//     public static double ty;
//     public static double tv;
//     public static double ta;

//     public static final String kDefaultAuto = "Default";
//     public static final String kCustomAuto = "My Auto";
//     public String m_autoSelected;
//     public final SendableChooser<String> m_chooser = new SendableChooser<>();
  
//     public boolean m_LimelightHasValidTarget = false;
//     public double m_LimelightDriveCommand = 0.0;
//     public double m_LimelightSteerCommand = 0.0;

//   public Limelight() {
//     // Vision Initialization
//     m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
//     m_chooser.addOption("My Auto", kCustomAuto);
//     SmartDashboard.putData("Auto choices", m_chooser);
      
//   }

//   public void refreshValues() {
//     // Read values periodically
//     tx = table.getEntry("tx").getDouble(0.0);
//     ty = table.getEntry("ty").getDouble(0.0);
//     tv = table.getEntry("tv").getDouble(0.0);
//     ta = table.getEntry("ta").getDouble(0.0);

//     // Post to smart dashboard periodically
//     SmartDashboard.putNumber("LimelightX", tx);
//     SmartDashboard.putNumber("LimelightY", ty);
//     SmartDashboard.putNumber("LimelightArea", ta);

//   }

//   public void updateTrackingData() {
//     // These numbers must be tuned for your Robot!  Be careful!
//     final double STEER_K = 0.02;                    // how hard to turn toward the target
//     final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
//     final double DESIRED_TARGET_AREA = 3.0;        // Area of the target when the robot reaches the wall
//     final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

//     refreshValues();

//     if (tv < 1.0) {
//       m_LimelightHasValidTarget = false;
//       m_LimelightDriveCommand = 0.0;
//       m_LimelightSteerCommand = 0.0;
//       return;
//     }

//     m_LimelightHasValidTarget = true;
//     System.out.println("TARGET FOUND");

//     // Start with proportional steering
//     double steer_cmd = tx * STEER_K;
//     m_LimelightSteerCommand = steer_cmd;

//     // try to drive forward until the target area reaches our desired area
//     double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

//     // don't let the robot drive too fast into the goal
//     if (drive_cmd > MAX_DRIVE) {
//       drive_cmd = MAX_DRIVE;
//     }
//     m_LimelightDriveCommand = drive_cmd;
//   }

//   // public void activateUSBCamera() {
//   //   table.getEntry("stream").setNumber(0);
//   // }

//   // public void turnOnLED() {
//   //   table.getEntry("ledMode").setNumber(3);
//   // }

//   // public void turnOffLED() {
//   //   table.getEntry("ledMode").setNumber(1);
//   // }

//   // public void blinkLED() {
//   //   table.getEntry("ledMode").setNumber(2);
//   // }
  
//   @Override
//   public void initDefaultCommand() {
//   }

// }