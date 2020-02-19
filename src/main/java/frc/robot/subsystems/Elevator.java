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

public class Elevator extends Subsystem {
    public Spark extendMotor;
    public Spark liftMotor;

    public Elevator() {
        extendMotor = new Spark(RobotMap.extendElevator);
        liftMotor = new Spark(RobotMap.liftRobot);
    }

    @Override
    public void initDefaultCommand() {
    }
}