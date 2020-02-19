/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  public static boolean toggleOn1 = false;
  public static boolean togglePressed1 = false;
  public static boolean toggleOn2 = false;
  public static boolean togglePressed2 = false;
  // DigitalInput forwardLimitSwitch = new DigitalInput(RobotMap.forwardLimitSwitch);
  // DigitalInput backwardLimitSwitch = new DigitalInput(RobotMap.backwardLimitSwitch);

  public static boolean fullPower = true;
  public static boolean power83 = false;
  public static boolean threeFourthsPower = false;

  // Motor Values
  public static int topShooter = 4;
  public static int bottomShooter = 2;
  public static int leftDrivetrain = 1;
  public static int rightDrivetrain = 0;
  public static int magazineMotor = 3;
  
  // Controller Port Values
  public static int XboxController0 = 0;
  public static int XboxController1 = 1;

  // Elevator Values
  public static int extendElevator = 5;
  public static int liftRobot = 6;
 
  // Encoder Values
  public static int leftDrivetrainEncoder1 = 0;
  public static int leftDrivetrainEncoder2 = 1;
  public static int rightDrivetrainEncoder1 = 2;
  public static int rightDrivetrainEncoder2 = 3;
  
  // Intake Values
  public static int intakeRoller = 7;
  public static int intakeExtender = 8;

  // Power Value
  public static double power = 0.8;

  // Shooter Power Value
  public static double shooterPower = 0.8;

  // Index Delay
  public static double indexDelay = 0.15;

  //Encoder Values
  public static double drivetrainEncoder_RadiansPerPulse = 2*3.14/512; //Check, assume encoders have 512 pulse/rev
  public static double drivetrainEncoder_MaxPeriod = 1.0;
  public static double drivetrainEncoder_MinRate = 6*3.14;

  public static boolean leftDrivetrainEncoder_Reverse = false;
  public static boolean rightDrivetrainEncoder_Reverse = true;

}