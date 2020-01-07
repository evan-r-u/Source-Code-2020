/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  public Spark leftMotor;
  public Spark rightMotor;
  public DifferentialDrive drivetrain;

  public Drivetrain() {
    leftMotor = new Spark(RobotMap.leftMotor.value);
    rightMotor = new Spark(RobotMap.rightMotor.value);
    drivetrain = new DifferentialDrive(leftMotor, rightMotor);
  }

  @Override
  public void initDefaultCommand() {
  }

  
}