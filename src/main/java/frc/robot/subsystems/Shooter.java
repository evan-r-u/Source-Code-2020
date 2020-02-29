/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Shooter extends Subsystem {
  public Spark topMotor;
  public Spark bottomMotor;

  public Shooter() {
    topMotor = new Spark(RobotMap.topShooter);
    bottomMotor = new Spark(RobotMap.bottomShooter);

    // Ensures that motors keep running during Timer.delay()
    topMotor.setSafetyEnabled(false);
    bottomMotor.setSafetyEnabled(false);

  }

  @Override
  public void initDefaultCommand() {
  }

  
}