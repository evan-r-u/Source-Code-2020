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

public class Lifter extends Subsystem {
    public Spark Lifter1;
    public Spark Lifter2;

    public Lifter() {
        Lifter1 = new Spark(RobotMap.Lifter1.value);
        Lifter2 = new Spark(RobotMap.Lifter2.value);
    }

    @Override
    public void initDefaultCommand() {
    }
}