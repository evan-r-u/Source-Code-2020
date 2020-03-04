/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;
// import frc.robot.subsystems.Limelight;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import java.lang.System;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static Intake intake;
  public static Elevator elevator;
  public static Magazine magazine;
  public static Shooter shooter;
  // public static Limelight limelight;
  public static double leftStickVal;
  public static double rightStickVal;
  public static Boolean autonomous;
  public static double level;
  public static Boolean changeAutonomous;
  public static NetworkTable table;
  public static NetworkTableEntry tx;
  public static NetworkTableEntry ty;
  public static NetworkTableEntry tv;
  public static NetworkTableEntry ta;
  // public static double tx;
  // public static double ty;
  // public static double tv;
  // public static double ta;
  public static Spark testSpark;
  public static double tempIndexDelay;
  
  public static XboxController XboxController0;
  public static XboxController XboxController1;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  public static String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  
  
  @Override
  public void robotInit() {
    // This is called once when the robot code initializes
    drivetrain = new Drivetrain();
    intake = new Intake();
    elevator = new Elevator();
    magazine = new Magazine();
    shooter = new Shooter();
    // limelight = new Limelight();

    XboxController0 = new XboxController(RobotMap.XboxController0);
    XboxController1 = new XboxController(RobotMap.XboxController1);
    
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);

    // Resets index delay
    RobotMap.indexDelayAdjusted = RobotMap.indexDelay;

    // Resets intake speed adjustment
    RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed;

    RobotMap.collectMode = false;

    // limelight.activateUSBCamera();
    // limelight.turnOffLED();

    // table.getEntry("ledMode").setNumber(1);


    // Vision Initialization

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

   

    

  }

  @Override
  public void robotPeriodic() {
    // This is called every period regardless of mode
    if (RobotMap.activateSensor) {
      magazine.checkColor();
    }
    SmartDashboard.putNumber("Number of Balls", RobotMap.numberOfBalls);
    System.out.println("Number of Balls");
    System.out.println(RobotMap.numberOfBalls);

    // limelight.refreshValues();
    // refreshValues();


    // elevator.liftMotor.set(RobotMap.liftPower);
  

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");

    // Activates USB camera connected to LimeLight
    table.getEntry("stream").setNumber(0);

    // Read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    // Post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

  @Override
  public void autonomousInit() {
    // This is called once when the robot first enters autonomous mode
    autonomous = true;
    changeAutonomous = false;
    super.autonomousInit();

    // limelight.m_autoSelected = limelight.m_chooser.getSelected();
    m_autoSelected = m_chooser.getSelected();

    // intake.intakeExtender.set(0.6);
    // Timer.delay(3);
    // intake.intakeExtender.set(0.0);

  }

  @Override
  public void autonomousPeriodic() {
    // This is called periodically while the robot is in autonomous mode
    super.autonomousPeriodic();
    
    System.out.println("Auto");

    updateTrackingData();

    // Rotates about z-axis until it finds a target
    if (m_LimelightHasValidTarget) {
      drivetrain.drivetrain.arcadeDrive(-1 * m_LimelightDriveCommand, -1 * m_LimelightSteerCommand);
    }
    else {
      drivetrain.turnAround();
    }
}

  @Override
  public void teleopInit() {
    // This is called once when the robot first enters teleoperated mode
  }

  @Override
  public void teleopPeriodic() {
    // This is called periodically while the robot is in teleopreated mode
    autonomous = false;
    changeAutonomous = false;
    Scheduler.getInstance().run();

    System.out.println(magazine.magazineEncoder.getDistance());
    // Drivetrain

    // limelight.updateTrackingData();

    updateTrackingData();

    if (XboxController1.getYButtonPressed()) {
      RobotMap.autoMode = ! RobotMap.autoMode;
    }

    if (RobotMap.autoMode) {
      // Turn on LED
      // table.getEntry("ledMode").setNumber(3);
      if (m_LimelightHasValidTarget) {
        drivetrain.drivetrain.arcadeDrive(-1 * m_LimelightDriveCommand, -1 * m_LimelightSteerCommand);
      }
      else {
        drivetrain.drivetrain.arcadeDrive(0, 0);
      }

      // Fine tuning - yaw (about z-axis)
      double fineYaw = RobotMap.fineDrivetrainPower * (Math.pow(XboxController1.getX(Hand.kRight), 3));

      drivetrain.drivetrain.arcadeDrive(0, fineYaw);

    } else {
      // Turn off LED
      // table.getEntry("ledMode").setNumber(1);

      double magnitudeLeft = RobotMap.drivetrainPower * (Math.pow(XboxController0.getY(Hand.kLeft), 3));
      double magnitudeRight = RobotMap.drivetrainPower * (Math.pow(XboxController0.getY(Hand.kRight), 3));

      if (RobotMap.collectMode) {
        drivetrain.drivetrain.tankDrive(-1 * magnitudeRight, -1 * magnitudeLeft);
      }

      else {
        drivetrain.drivetrain.tankDrive(magnitudeLeft, magnitudeRight);
      }
    }


    // Shooter
  
    shooter.topMotor.set(XboxController0.getTriggerAxis(Hand.kRight) * RobotMap.shooterPower);
    shooter.bottomMotor.set(XboxController0.getTriggerAxis(Hand.kRight) * RobotMap.shooterPower);

    // Intake

    intake.intakeRoller.set(XboxController0.getTriggerAxis(Hand.kLeft) * RobotMap.intakeSpeed);
  

    // Left Bumper - Straight Forward
    while (XboxController0.getBumper(Hand.kLeft)) {
      drivetrain.drivetrain.tankDrive(RobotMap.drivetrainPower * -1 , RobotMap.drivetrainPower * -1);
    }

    // Right Bumper - Straight Backward
    while (XboxController0.getBumper(Hand.kRight)) {
      drivetrain.drivetrain.tankDrive(RobotMap.drivetrainPower, RobotMap.drivetrainPower);
    }

    // Run magazine forward
    if (XboxController0.getXButtonPressed()) {
      magazine.magazineSpark.set(RobotMap.magazinePower);
    }

    if (XboxController0.getXButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }

    // Run magazine backward
    if (XboxController0.getYButtonPressed()) {
      magazine.magazineSpark.set(-1 * RobotMap.magazinePower);
    }

    if (XboxController0.getYButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }

    if (XboxController1.getXButtonPressed()) {
      RobotMap.collectMode = ! RobotMap.collectMode;
    }


    if (XboxController1.getAButton()) {
      System.out.println("Extending...");
      elevator.extendMotor.set(RobotMap.extendPower);
    }
    else {
      elevator.extendMotor.set(0.0);
    }
  
    if (XboxController1.getBButton()) {
      System.out.println("Lifting...");
      elevator.liftMotor.set(RobotMap.liftPower);
    }
    else {
      elevator.liftMotor.set(0.0);
    }
  
    // Extend intake roller
    while (XboxController1.getBumperPressed(Hand.kLeft)) {
      intake.intakeExtender.set(RobotMap.rollerExtendPower);
    }
  
    while (XboxController1.getBumperReleased(Hand.kLeft)) {
      intake.intakeExtender.set(0.0);
    }
  
  
    // Retract intake roller
    while (XboxController1.getBumperPressed(Hand.kRight)) {
      intake.intakeExtender.set(-1 * RobotMap.rollerExtendPower);
    }
  
    while (XboxController1.getBumperReleased(Hand.kRight)) {
      intake.intakeExtender.set(0.0);
    }




    // Start Button - Shoot One Ball
    if (XboxController0.getStartButtonPressed()) {
      RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed;
      if (RobotMap.rotationMode) {
        magazine.rotateMagazine();
        RobotMap.numberOfBalls--;
      }
      else {
        // intake.intakeRoller.set(RobotMap.intakeSpeedAdjusted);
        magazine.shootBall();
      }
      
    }

    if (XboxController0.getStartButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }



    // Back Button - Index One Ball
    if (XboxController0.getBackButtonPressed()) {
      if (RobotMap.rotationMode) {
        magazine.rotateMagazine();
        RobotMap.numberOfBalls++;

      }
      else if (! RobotMap.activateSensor) {
        // if (RobotMap.numberOfBalls == 2) {
        //   // RobotMap.numberOfBalls++;
        //   RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed / 2;
        // }
        
        if (RobotMap.numberOfBalls == 0) {
          tempIndexDelay = RobotMap.indexDelayAdjusted * 0.8;
        }
        if (RobotMap.numberOfBalls == 1) {
          tempIndexDelay = RobotMap.indexDelayAdjusted;
          // intake.intakeRoller.set(RobotMap.intakeSpeedAdjusted * 1.2);
        }
        if (RobotMap.numberOfBalls == 2) {
          tempIndexDelay = RobotMap.indexDelayAdjusted * 0.4;
        }
        if (RobotMap.numberOfBalls == 3) {
          tempIndexDelay = RobotMap.indexDelayAdjusted * 0.85;
        }
        // RobotMap.numberOfBalls++;
        magazine.magazineSpark.set(RobotMap.magazinePower);
        Timer.delay(tempIndexDelay);
        // RobotMap.numberOfBalls++;
        // magazine.magazineSpark.set(0.5);
        // Timer.delay(RobotMap.indexDelayAdjusted);
        magazine.magazineSpark.set(0.0);

      RobotMap.numberOfBalls++;
      }
    }

      
      // if (RobotMap.numberOfBalls >= 3 && ! RobotMap.delayIsAdjusted) {
      //   RobotMap.indexDelayAdjusted -= 0.12;
      //   RobotMap.delayIsAdjusted = true;
      // }
    

    if (XboxController0.getBackButtonReleased()) {
      magazine.magazineSpark.set(0.0);
      }

  }


    // if (XboxController0.getBackButtonPressed()) {
    //   // if (RobotMap.numberOfBalls == 2) {
    //   //   // RobotMap.numberOfBalls++;
    //   //   RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed / 2;
    //   // }
    //   if (RobotMap.numberOfBalls == 0) {
    //     tempIndexDelay = RobotMap.indexDelayAdjusted * 0.65;
    //   }
    //   if (RobotMap.numberOfBalls == 1) {
    //     tempIndexDelay = RobotMap.indexDelayAdjusted;
    //     // intake.intakeRoller.set(RobotMap.intakeSpeedAdjusted * 1.2);
    //   }
    //   if (RobotMap.numberOfBalls == 2) {
    //     tempIndexDelay = RobotMap.indexDelayAdjusted;
    //   }
    //   if (RobotMap.numberOfBalls == 3) {
    //     tempIndexDelay = RobotMap.indexDelayAdjusted;
    //   }
    //   // RobotMap.numberOfBalls++;
    //   magazine.magazineSpark.set(RobotMap.magazinePower);
    //   Timer.delay(tempIndexDelay);
    //   // RobotMap.numberOfBalls++;
    //   // magazine.magazineSpark.set(RobotMap.magazinePower);
    //   // Timer.delay(RobotMap.indexDelayAdjusted);
    //   magazine.magazineSpark.set(0.0);
    //   RobotMap.numberOfBalls--;
    // }

    // if (XboxController0.getBackButtonReleased()) {
    //   magazine.magazineSpark.set(0.0);
    // }


  // while (XboxController1.getAButtonPressed()) {
  //   System.out.println("Extending...");
  //   elevator.extendMotor.set(RobotMap.extendPower);
  // }

  

  public void updateTrackingData() {
    final double STEER_K = 0.1;                         // how hard to turn toward the target
    final double DRIVE_K = 0.4;                         // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 1.7;             // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = RobotMap.drivetrainPower;  // Simple speed limit so we don't drive too fast

    // Read values periodically
    // tx = table.getEntry("tx").getDouble(0.0);
    // ty = table.getEntry("ty").getDouble(0.0);
    // tv = table.getEntry("tv").getDouble(0.0);
    // ta = table.getEntry("ta").getDouble(0.0);

    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");

    // Activates USB camera connected to LimeLight
    table.getEntry("stream").setNumber(0);

    // Read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double v = tv.getDouble(0.0);
    double area = ta.getDouble(0.0);



    // Post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    if (v < 1.0) {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;
    System.out.println("TARGET FOUND");

    // Start with proportional steering
    double steer_cmd = x * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - area) * DRIVE_K;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
  }
  


  @Override
  public void testInit() {
    // This is called once when the robot enters test mode
  }

  @Override
  public void testPeriodic() {
    // This is called periodically while the robot is in test mode
  }

}
