/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import java.lang.System;

public class Drivetrain extends Subsystem {
  public VictorSP leftMotor;
  public VictorSP rightMotor;
  public Encoder leftEncoder;
  public Encoder rightEncoder;
  public DifferentialDrive drivetrain;

  public Drivetrain() {
    leftMotor = new VictorSP(RobotMap.leftDrivetrain);
    rightMotor = new VictorSP(RobotMap.rightDrivetrain);
    drivetrain = new DifferentialDrive(leftMotor, rightMotor);
    leftEncoder = new Encoder(RobotMap.leftDrivetrainEncoderA, RobotMap.leftDrivetrainEncoderB, RobotMap.leftDrivetrainEncoder_Reverse, Encoder.EncodingType.k2X);
    rightEncoder = new Encoder(RobotMap.rightDrivetrainEncoderA, RobotMap.rightDrivetrainEncoderB, RobotMap.rightDrivetrainEncoder_Reverse, Encoder.EncodingType.k2X);
    
    // Configures the encoder's distance-per-pulse
    // The robot moves forward 1 foot per encoder rotation
    // There are 256 pulses per encoder rotation
    leftEncoder.reset();
    leftEncoder.setDistancePerPulse(1./256.);
    leftEncoder.setMaxPeriod(RobotMap.drivetrainEncoder_MaxPeriod);
    leftEncoder.setMinRate(RobotMap.drivetrainEncoder_MinRate);
    leftEncoder.setDistancePerPulse(RobotMap.drivetrainEncoder_RadiansPerPulse);
    leftEncoder.setSamplesToAverage(7);


    rightEncoder.reset();
    rightEncoder.setDistancePerPulse(1./256.);
    rightEncoder.setMaxPeriod(RobotMap.drivetrainEncoder_MaxPeriod);
    rightEncoder.setMinRate(RobotMap.drivetrainEncoder_MinRate);
    rightEncoder.setDistancePerPulse(RobotMap.drivetrainEncoder_RadiansPerPulse);
    rightEncoder.setSamplesToAverage(7);

    leftMotor.setInverted(true);
    rightMotor.setInverted(true);
    
    // Ensures that motors keep running during Timer.delay()
    drivetrain.setSafetyEnabled(false);

  }

  public void driveRoute() {
    // drivetrain.tankDrive(0.4, 0.4);
    // Timer.delay(2);
    // // turn right
    // drivetrain.tankDrive(0.4, 0);
    // Timer.delay(1);
    // drivetrain.tankDrive(0.4, 0.4);

    
    System.out.print("Left Encoder Distance -");
    System.out.print(leftEncoder.getDistance());
    System.out.print("\nRight Encoder Distance -");
    System.out.print(rightEncoder.getDistance());

    // double error = Math.abs(leftEncoder.getDistance() - rightEncoder.getDistance());
    // double kP = 1;

    // Drives forward at half speed until the robot has moved 5 feet, then stops
    if(leftEncoder.getDistance() < 5) {
      // Drives forward continuously at half speed, using the encoders to stabilize the heading
      drivetrain.tankDrive(-0.51, -0.5);
    } else {
      drivetrain.tankDrive(0, 0);
    }
  }

  public void turnAround() {
    drivetrain.arcadeDrive(0, 0.6);
  }


  @Override
  public void initDefaultCommand() {
  }

  
}