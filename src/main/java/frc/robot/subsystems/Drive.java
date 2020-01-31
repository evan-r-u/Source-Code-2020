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
// import edu.wpi.first.wpilibj


import frc.robot.RobotMap;

public class Drive extends Subsystem {
    int P, I, D = 1;
    int integral, previous_error, setpoint = 0;
    Gyro gyro;
    DifferentialDrive Drivechain;

    public Drive(Gyro gyro) {
        this.gyro = gyro;
    }

    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }

    public void PID(){
        error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute()
    {
        PID();
        robotDrive.tankDrive(0, rcw);
    }
    }