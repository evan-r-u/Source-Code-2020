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
import frc.robot.OI;
// import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


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
  // public static Puncher puncher;
  public static OI oi;
  public static Magazine magazine;
  public static Shooter shooter;
  // public static Encoder drivetrainEncoder;
  public double leftStickVal;
  public double rightStickVal;
  public static Boolean autonomous;
  public static double level;
  public static Boolean changeAutonomous;
  public NetworkTable table;
  public NetworkTableEntry tx;
  public NetworkTableEntry ty;
  public NetworkTableEntry ta;
  public Spark testSpark;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // private VictorSP m_Left0 = new VictorSP(0);
  // private VictorSP m_Left1 = new VictorSP(1);
  // private VictorSP m_Right0 = new VictorSP(2);
  // private VictorSP m_Right1 = new VictorSP(3);
  // private SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(m_Left0,m_Left1);
  // private SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(m_Right0,m_Right1);
  // private DifferentialDrive m_Drive = new DifferentialDrive(m_LeftMotors,m_RightMotors);

  // private XboxController m_Controller = new XboxController(0);

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  



  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */


  

  // SpeedControllerGroup leftMotors = new SpeedControllerGroup(left1, left2);
  // SpeedControllerGroup rightMotors = new SpeedControllerGroup(right1, right2);

  // DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  // @Override
  // public void robotInit() {
     
  // }

  
  @Override
  public void robotInit() {
    // This is called once when the robot code initializes
    drivetrain = new Drivetrain();
    intake = new Intake();
    elevator = new Elevator();
    // puncher = new Puncher();
    oi = new OI();
    magazine = new Magazine();
    shooter = new Shooter();


    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);


    // Configures the encoder's distance-per-pulse
    // The robot moves forward 1 foot per encoder rotation
    // There are 256 pulses per encoder rotation
    drivetrain.leftEncoder.setDistancePerPulse(1./256.);
    drivetrain.rightEncoder.setDistancePerPulse(1./256.);



    // Vision initialization

    // CHANGE THIS NAME

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");

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
  public void robotPeriodic() {
    // This is called every period regardless of mode
    // drivetrain.drivetrain.tankDrive(-0.7, -0.7);
    shooter.topMotor.set(0.8);
    shooter.bottomMotor.set(0.8);

    // elevator.extendMotor.set(0.6);

    magazine.checkColor();
  }

  @Override
  public void autonomousInit() {
    // This is called once when the robot first enters autonomous mode
    autonomous = true;
    changeAutonomous = false;
    super.autonomousInit();

    m_autoSelected = m_chooser.getSelected();

    drivetrain.driveRoute();
  }

  @Override
  public void autonomousPeriodic() {
    // This is called periodically while the robot is in autonomous mode
    super.autonomousPeriodic();

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

    // lifter.Lifter1.set(oi.XboxController1.getY(Hand.kRight));
    // lifter.Lifter2.set(oi.XboxController1.getY(Hand.kLeft));
    
    // // Pivot Control
    // intake.intakePivot.set(oi.XboxController1.getTriggerAxis(Hand.kRight)-oi.XboxController1.getTriggerAxis(Hand.kLeft));
    
    // // Punch Control
    // if (oi.toggleOn1) {
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
    // intake.intakeRoller.set(oi.XboxController2.getTriggerAxis(Hand.kRight)-oi.XboxController2.getTriggerAxis(Hand.kLeft));
    // intake.intakeRoller.set(-0.3);
      
    // Power Levels
    if (oi.fullPower) {
      level = 1;
      System.out.println("Full power");
      SmartDashboard.putString("Power", "100%");
    }
    else if (oi.power83) {
      level = 0.83;
      System.out.println("83% power");
      SmartDashboard.putString("Power", "83%");
    }
    else if (oi.threeFourthsPower) {
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

    double magnitudeLeft = Math.pow((oi.XboxController2.getY(Hand.kLeft)), 3) * level;
    double magnitudeRight = Math.pow((oi.XboxController2.getY(Hand.kRight)), 3) * level;
    drivetrain.drivetrain.tankDrive(magnitudeLeft, magnitudeRight);

    // Update_Limelight_Tracking();

    // double steer = oi.XboxController1.getX(Hand.kRight);
    // double drive = -oi.XboxController1.getY(Hand.kLeft);
    // boolean auto = oi.XboxController1.getAButton();

    // steer *= 0.70;
    // drive *= 0.70;

    // if (auto) {
    //   if (m_LimelightHasValidTarget)
    //   {
    //         drivetrain.drivetrain.tankDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);
    //   }
    //   else
    //   {
    //         drivetrain.drivetrain.tankDrive(0.0,0.0);
    //   }
    // }
    // else {
    //   drivetrain.drivetrain.tankDrive(drive,steer);
    // }


  
    
    // if (oi.toggleOn2) {
    //   // Punch
    //   puncher.punchBackward.set(false);
    //   puncher.punchForward.set(true);
    // } else{
    //   // Retract punch
    //   puncher.punchForward.set(false);
    //   puncher.punchBackward.set(true);
    // }
  }


	private void updateToggle() {
    if(oi.XboxController1.getRawButton(1)){
      if(!oi.togglePressed1){
          oi.toggleOn1 = !oi.toggleOn1;
          oi.togglePressed1 = true;
      }
      }else{
          oi.togglePressed1 = false;
      }
  
  if(oi.XboxController2.getRawButton(1)){
    if(!oi.togglePressed2){
        oi.toggleOn2 = !oi.toggleOn2;
        oi.togglePressed2 = true;
    }
    }else{
        oi.togglePressed2 = false;
    }

  // Left Bumper - Forward
  while (oi.XboxController2.getBumper(Hand.kLeft)) {
    drivetrain.drivetrain.tankDrive(RobotMap2.power.value, RobotMap2.power.value);

  }

  // Right Bumper - Backward
  while (oi.XboxController2.getBumper(Hand.kRight)) {
    drivetrain.drivetrain.tankDrive(RobotMap2.power.value * -1 , RobotMap2.power.value * -1);
  
  }

  // 100% Speed - A
  while (oi.XboxController2.getAButtonPressed()) {
    oi.fullPower = true;
    oi.power83 = false;
    oi.threeFourthsPower = false;
  }

  // 83% Speed - B
  while (oi.XboxController2.getBButtonPressed()) {
    oi.fullPower = false;
    oi.power83 = true;
    oi.threeFourthsPower = false;
  }

  // 75% Speed - Y
  while (oi.XboxController2.getYButtonPressed()) {
    oi.fullPower = false;
    oi.power83 = false;
    oi.threeFourthsPower = true;
    if (autonomous) {
      changeAutonomous = true;
      level = 0.75;
    }
  
  // Extend elevator - left bumper hold
  while (oi.XboxController1.getBumper(Hand.kLeft)) {
    elevator.extendMotor.set(0.5);
  }

  while (oi.XboxController1.getXButtonPressed()) {
    System.out.println("Extending...");
    elevator.extendMotor.set(0.6);
  }

  // Lift robot - right bumper hold
  while (oi.XboxController1.getBumper(Hand.kRight)) {
    elevator.liftMotor.set(0.5);
  }



  }

//   while (oi.XboxController2.getXButtonPressed()) {
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

  }
// }


// public void Update_Limelight_Tracking()
// {
//       // These numbers must be tuned for your Robot!  Be careful!
//       final double STEER_K = 0.03;                    // how hard to turn toward the target
//       final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
//       final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
//       final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

//       double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
//       double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
//       double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
//       double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

//       if (tv < 1.0)
//       {
//         m_LimelightHasValidTarget = false;
//         m_LimelightDriveCommand = 0.0;
//         m_LimelightSteerCommand = 0.0;
//         return;
//       }

//       m_LimelightHasValidTarget = true;

//       // Start with proportional steering
//       double steer_cmd = tx * STEER_K;
//       m_LimelightSteerCommand = steer_cmd;

//       // try to drive forward until the target area reaches our desired area
//       double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

//       // don't let the robot drive too fast into the goal
//       if (drive_cmd > MAX_DRIVE)
//       {
//         drive_cmd = MAX_DRIVE;
//       }
//       m_LimelightDriveCommand = drive_cmd;
// }

  @Override
  public void testInit() {
    // This is called once when the robot enters test mode
  }

  @Override
  public void testPeriodic() {
    // This is called periodically while the robot is in test mode
  }

}
