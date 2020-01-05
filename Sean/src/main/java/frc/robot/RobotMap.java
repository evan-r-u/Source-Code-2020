/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public enum RobotMap {
  // Motor Values
  leftMotor(1),
  rightMotor(0),
  // Control Value
  XboxController1(0),
  XboxController2(1),
  // Lifter Values
  Lifter1(5),
  Lifter2(6),
  // Punch Values
  punchForward(2),
  punchBackward(1),
  // Intake Value
  intakePivot(7),
  // Intake Roller Value
  intakeRoller(8),
  // Limit Switch Values
  forwardLimitSwitch(1),
  backwardLimitSwitch(2);

  public final int value;

  RobotMap(int value) {
    this.value = value;
  }
}


