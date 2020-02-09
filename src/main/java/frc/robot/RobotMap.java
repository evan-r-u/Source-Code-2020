/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public enum RobotMap {
  // Motor Values
  topShooter(3),
  bottomShooter(2),
  leftDrivetrain(1),
  rightDrivetrain(0),
  magazineMotor(4),
  // Control Value
  XboxController1(0),
  XboxController2(1),
  // Elevator Values
  // extendElevator(5),
  extendElevator(5),
  liftRobot(6),
  // Encoder Values
  leftDrivetrainEncoder1(0),
  leftDrivetrainEncoder2(1),
  rightDrivetrainEncoder1(2),
  rightDrivetrainEncoder2(3),
  // Intake Roller Value
  intakeRoller(7);
  // extendElevator(5),
  // // Punch Values
  // punchForward(2),
  // punchBackward(1),
  // // Intake Value
  // intakePivot(7),
  // // Limit Switch Values
  // forwardLimitSwitch(1),
  // backwardLimitSwitch(2);

  public final int value;

  RobotMap(int value) {
    this.value = value;
  }
}


