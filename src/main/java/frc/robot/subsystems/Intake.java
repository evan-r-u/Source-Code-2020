/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem {
    public Spark intakeRoller;
    public Servo intakeExtender;

    public Intake() {
        intakeRoller = new Spark(RobotMap.intakeRoller.value);
        intakeExtender = new Servo(RobotMap.intakeExtender.value);
    }

    @Override
    public void initDefaultCommand() {
    }
}
