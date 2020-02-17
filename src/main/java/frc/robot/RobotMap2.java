/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public enum RobotMap2 {
    // Power Value
    power(0.8),

    // Shooter Power Value
    shooterPower(0.8),

    //Encoder Values
    drivetrainEncoder_RadiansPerPulse(2*3.14/512), //Check, assume encoders have 512 pulse/rev
    drivetrainEncoder_MaxPeriod(1.0),
    drivetrainEncoder_MinRate(6*3.14);
   
    
    public final double value;
  
    RobotMap2(double value) {
      this.value = value;
    }
  }