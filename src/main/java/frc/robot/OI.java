/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
  public XboxController XboxController1 = new XboxController(RobotMap.XboxController1.value);
  public XboxController XboxController2 = new XboxController(RobotMap.XboxController2.value);
  public boolean toggleOn1 = false;
  public boolean togglePressed1 = false;
  public boolean toggleOn2 = false;
  public boolean togglePressed2 = false;
  DigitalInput forwardLimitSwitch = new DigitalInput(RobotMap.forwardLimitSwitch.value);
  DigitalInput backwardLimitSwitch = new DigitalInput(RobotMap.backwardLimitSwitch.value);

  public boolean fullPower = false;
  public boolean power83 = false;
  public boolean threeFourthsPower = true;
}