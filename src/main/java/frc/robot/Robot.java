/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Puncher;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.cameraserver.*;
// import frc.robot.OI;
// import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import java.lang.System;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
  // public static OI RobotMap;
  public static Magazine magazine;
  public static Shooter shooter;
  public static double leftStickVal;
  public static double rightStickVal;
  public static Boolean autonomous;
  public static double level;
  public static Boolean changeAutonomous;
  public static NetworkTable table;
  public static NetworkTableEntry tx;
  public static NetworkTableEntry ty;
  public static NetworkTableEntry ta;
  public static Spark testSpark;
  
  public static XboxController XboxController0;
  public static XboxController XboxController1;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
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
    // puncher = new Puncher();
    // RobotMap = new OI();
    magazine = new Magazine();
    shooter = new Shooter();

    XboxController0 = new XboxController(RobotMap.XboxController0);
    XboxController1 = new XboxController(RobotMap.XboxController1);
    


    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);


    // Extends intake
    // intake.intakeExtender.set(0.5);

    // Activates intake
    intake.intakeRoller.set(0.45);



    // Vision initialization

    // CHANGE THIS NAME

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

  }

  @Override
  public void robotPeriodic() {
    // This is called every period regardless of mode
    // drivetrain.drivetrain.tankDrive(-0.7, -0.7);
    // shooter.topMotor.set(0.8);
    // shooter.bottomMotor.set(0.8);

    // elevator.extendMotor.set(0.6);
    // elevator.liftMotor.set(0.8);

    magazine.checkColor();

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    // System.out.println("Vision Data");
    // System.out.println(tx);
    // System.out.println(ty);
    // System.out.println(ta);

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
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

    m_autoSelected = m_chooser.getSelected();

    // drivetrain.turnAround();

    // drivetrain.driveRoute();
  }

  @Override
  public void autonomousPeriodic() {
    // This is called periodically while the robot is in autonomous mode
    super.autonomousPeriodic();
    
    System.out.println("Auto");
    // drivetrain.driveRoute();

    // teleopPeriodic();
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
    updateToggle();
    
    /*Controller 1*/

    // lifter.Lifter1.set(XboxController1.getY(Hand.kRight));
    // lifter.Lifter2.set(XboxController1.getY(Hand.kLeft));
    
    // // Pivot Control
    // intake.intakePivot.set(XboxController1.getTriggerAxis(Hand.kRight)-XboxController1.getTriggerAxis(Hand.kLeft));
    
    // // Punch Control
    // if (RobotMap.toggleOn1) {
    //   // Punch
    //   puncher.punchBackward.set(false);
    //   puncher.punchForward.set(true);
    // } else{
    //   // Retract punch
    //   puncher.punchForward.set(false);
    //   puncher.punchBackward.set(true);
    // }

    
    /*Controller 2*/

  
    // Intake Roller    
    // intake.intakeRoller.set(XboxController0.getTriggerAxis(Hand.kRight)-XboxController0.getTriggerAxis(Hand.kLeft));
    // intake.intakeRoller.set(-0.3);
      
    // Power Levels
    if (RobotMap.fullPower) {
      level = 1;
      System.out.println("Full power");
      SmartDashboard.putString("Power", "100%");
    }
    else if (RobotMap.power83) {
      level = 0.83;
      System.out.println("83% power");
      SmartDashboard.putString("Power", "83%");
    }
    else if (RobotMap.threeFourthsPower) {
      if (autonomous) {
        level = 0.65;
      }
      else if (autonomous && changeAutonomous) {
        level = 0.75;
      }
      else {
        level = 0.75;
      }
      System.out.println("3/4 power");
      SmartDashboard.putString("Power", "75%");
    }

    // double magnitudeLeft = Math.pow((XboxController0.getY(Hand.kLeft)), 3) * level;
    // double magnitudeRight = Math.pow((XboxController0.getY(Hand.kRight)), 3) * level;

    // double magnitudeLeft = (Math.pow(2, XboxController0.getY(Hand.kLeft)) - 1) * level;
    // double magnitudeRight = (Math.pow(2, XboxController0.getY(Hand.kRight)) - 1) * level;

    double magnitudeLeft = 0.7 * (Math.pow(XboxController0.getY(Hand.kLeft), 3));
    double magnitudeRight = 0.7 * (Math.pow(XboxController0.getY(Hand.kRight), 3));

    drivetrain.drivetrain.tankDrive(magnitudeLeft, magnitudeRight);

    elevator.liftMotor.set(XboxController1.getY(Hand.kLeft));
    elevator.extendMotor.set(XboxController1.getY(Hand.kRight));

    // double rightTriggerValue = XboxController0.getTriggerAxis(Hand.kRight);
  
    shooter.topMotor.set(XboxController0.getTriggerAxis(Hand.kRight) * RobotMap.shooterPower);
    shooter.bottomMotor.set(XboxController0.getTriggerAxis(Hand.kRight) * RobotMap.shooterPower);
    
    
    System.out.println("Trigger Data");
    System.out.println(XboxController0.getTriggerAxis(Hand.kRight));


    Update_Limelight_Tracking();

    // double steer = XboxController1.getX(Hand.kRight);
    // double drive = -XboxController1.getY(Hand.kLeft);
    
    // Should this be a continuous hold or one-time push?
    boolean auto = XboxController1.getYButton();

    // boolean auto = false;

    // steer *= 0.70;
    // drive *= 0.70;

    if (auto) {
      if (m_LimelightHasValidTarget) {
        drivetrain.drivetrain.arcadeDrive(-m_LimelightDriveCommand, -m_LimelightSteerCommand);
      }
      else {
        drivetrain.drivetrain.arcadeDrive(0.0, 0.0);
      }
    }

    // Shoot here
    // shooter.bottomMotor.set(0.0);
    // shooter.topMotor.set(0.0);


    if(XboxController0.getRawButton(1)){
      if(!RobotMap.togglePressed2){
          RobotMap.toggleOn2 = !RobotMap.toggleOn2;
          RobotMap.togglePressed2 = true;
      } else{
          RobotMap.togglePressed2 = false;
      }
    }

    // Left Bumper - Forward
    while (XboxController0.getBumper(Hand.kLeft)) {
      // drivetrain.drivetrain.tankDrive(RobotMap.power, RobotMap.power);
      System.out.println("Extending...");
      elevator.extendMotor.set(0.5);
      
    }

    // Right Bumper - Backward
    while (XboxController0.getBumper(Hand.kRight)) {
      // drivetrain.drivetrain.tankDrive(RobotMap.power * -1 , RobotMap.power * -1);
      System.out.println("Lifting...");
      elevator.liftMotor.set(-0.5);
    }

    // 100% Speed - A
    while (XboxController0.getAButtonPressed()) {
      RobotMap.fullPower = true;
      RobotMap.power83 = false;
      RobotMap.threeFourthsPower = false;
    }

    // 83% Speed - B
    while (XboxController0.getBButtonPressed()) {
      RobotMap.fullPower = false;
      RobotMap.power83 = true;
      RobotMap.threeFourthsPower = false;
    }

    // 75% Speed - Y
    // while (XboxController0.getYButtonPressed()) {
    //   // RobotMap.fullPower = false;
    //   // RobotMap.power83 = false;
    //   // RobotMap.threeFourthsPower = true;
    //   // if (autonomous) {
    //   //   changeAutonomous = true;
    //   //   level = 0.75;
    //   // }
    //   magazine.magazineSpark.set(-0.5);
    // }

    if (XboxController0.getXButtonPressed()) {
      magazine.magazineSpark.set(0.5);
    }

    if (XboxController0.getXButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }

    if (XboxController0.getYButtonPressed()) {
      magazine.magazineSpark.set(-0.5);
    }

    if (XboxController0.getYButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }

    if (XboxController0.getStartButtonPressed()) {
      magazine.indexBall();
    }

    if (XboxController0.getStartButtonReleased()) {
      magazine.magazineSpark.set(0.0);
    }



    // // Empty Magazine - X
    // while (XboxController0.getXButtonPressed()) {
    //   magazine.magazineSpark.set(0.5);
    // }

    while (XboxController1.getAButtonPressed()) {
      System.out.println("Extending...");
      elevator.extendMotor.set(0.5);
    }

    while (XboxController1.getBButtonPressed()) {
      System.out.println("Lifting...");
      elevator.liftMotor.set(-0.5);
    }

    // magazine.magazineSpark.set(0.0);
  
  }

    // if (RobotMap.toggleOn2) {
    //   // Punch
    //   puncher.punchBackward.set(false);
    //   puncher.punchForward.set(true);
    // } else{
    //   // Retract punch
    //   puncher.punchForward.set(false);
    //   puncher.punchBackward.set(true);
    // }


	private void updateToggle() {
    if(XboxController1.getRawButton(1)){
      if(!RobotMap.togglePressed1){
          RobotMap.toggleOn1 = !RobotMap.toggleOn1;
          RobotMap.togglePressed1 = true;
      }
      }else{
          RobotMap.togglePressed1 = false;
      }
    }


  // elevator.extendMotor.set(0.0);
  // elevator.liftMotor.set(0.0);
  
  // // Extend elevator - left bumper hold
  // while (XboxController1.getBumper(Hand.kLeft)) {
  //   elevator.extendMotor.set(0.5);
  // }

  // while (XboxController1.getXButtonPressed()) {
  //   System.out.println("Extending...");
  //   elevator.extendMotor.set(0.6);
  // }

  // // Lift robot - right bumper hold
  // while (XboxController1.getBumper(Hand.kRight)) {
  //   elevator.liftMotor.set(0.5);
  // }




//   while (XboxController0.getXButtonPressed()) {
//     // come into range, aim, and align

//     float KpAim = -0.1f;
//     float KpDistance = -0.1f;
//     float min_aim_command = 0.05f;

    // std::shared_ptr<NetworkTable> table = NetworkTable::GetTable("limelight");
    // float tx = table->GetNumber("tx");
    // float ty = table->GetNumber("ty");


    // NetworkTableEntry tx = table.getEntry("tx");
    // NetworkTableEntry ty = table.getEntry("ty");
    // double x = tx.getDouble(0.0);
    // double y = ty.getDouble(0.0);
    

// // if (joystick->GetRawButton(9))
// // {
//     float heading_error = -tx;
//     float distance_error = -ty;
//     float steering_adjust = 0.0f;

//     if (tx > 1.0)
//     {
//             steering_adjust = KpAim*heading_error - min_aim_command;
//     }
//     else if (tx < 1.0)
//     {
//             steering_adjust = KpAim*heading_error + min_aim_command;
//     }

//     float distance_adjust = KpDistance * distance_error;

//     left_command += steering_adjust + distance_adjust;
//     right_command -= steering_adjust + distance_adjust;
// // }


    // single test shot based on autonomous calculations


// }


public void Update_Limelight_Tracking() {
  // These numbers must be tuned for your Robot!  Be careful!
  final double STEER_K = 0.02;                    // how hard to turn toward the target
  final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
  final double DESIRED_TARGET_AREA = 3.0;        // Area of the target when the robot reaches the wall
  final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

  double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0);
  double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
  double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
  double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);

  if (tv < 1.0) {
    m_LimelightHasValidTarget = false;
    m_LimelightDriveCommand = 0.0;
    m_LimelightSteerCommand = 0.0;
    return;
  }

  m_LimelightHasValidTarget = true;
  System.out.println("TARGET FOUND");

  // Start with proportional steering
  double steer_cmd = tx * STEER_K;
  m_LimelightSteerCommand = steer_cmd;

  // try to drive forward until the target area reaches our desired area
  double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

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
