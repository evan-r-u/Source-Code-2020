/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Puncher extends Subsystem {
    public Solenoid punchForward;
    public Solenoid punchBackward;

    public Puncher() {
        punchForward = new Solenoid(RobotMap.punchForward.value);
        punchBackward = new Solenoid(RobotMap.punchBackward.value);
    }

    @Override
    public void initDefaultCommand() {
    }
}