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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import java.lang.System;

public class Drivetrain extends Subsystem {
  public VictorSP leftMotor;
  public VictorSP rightMotor;
  public Encoder leftEncoder;
  public Encoder rightEncoder;
  public DifferentialDrive drivetrain;

  public Drivetrain() {
    leftMotor = new VictorSP(RobotMap.leftDrivetrain.value);
    rightMotor = new VictorSP(RobotMap.rightDrivetrain.value);
    drivetrain = new DifferentialDrive(leftMotor, rightMotor);
    leftEncoder = new Encoder(RobotMap.leftDrivetrainEncoder1.value, RobotMap.leftDrivetrainEncoder2.value);
    rightEncoder = new Encoder(RobotMap.rightDrivetrainEncoder1.value, RobotMap.rightDrivetrainEncoder2.value);

    leftMotor.setInverted(true);
    rightMotor.setInverted(true);
    
    // ensures that motors keep running during Timer.delay()
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

    // Drives forward at half speed until the robot has moved 5 feet, then stops
    if(leftEncoder.getDistance() < 5 && rightEncoder.getDistance() < 5) {
      drivetrain.tankDrive(0.4, 0.4);
    } else {
      drivetrain.tankDrive(0, 0);
    }
  }
  @Override
  public void initDefaultCommand() {
  }

  
}