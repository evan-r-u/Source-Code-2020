// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.VictorSP;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj.PWMSpeedController;
// import frc.robot.RobotMap;
// import frc.robot.RobotMap;
// import frc.robot.RobotMap;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.Encoder;
// import java.lang.System;

// public class PIDMotor extends PIDController {
//     /** The motor that will be set based on the {@link PIDController} results. */
//     public PWMSpeedController motor;
//     private double previousOutput = 0.0;
//     private double rampBand;
//     private double output;

//     /**
//      * Constructor for a PID controlled motor, with a controllable multiplier.
//      *
//      * @param motor The motor being set.
//      * @param rampBand The acceptable range for a motor change in one loop
//      */
//     public PIDMotor(PWMSpeedController motor, double rampBand) {
//         this.motor = motor;
//         this.rampBand = rampBand;
//     }

//     public void pidWrite(double pidInput) {
//         if (Math.abs(pidInput - previousOutput) > rampBand) { //If the change is greater that we want
//             output = pidInput - previousOutput > 0 ? previousOutput + rampBand : previousOutput - rampBand; //set output to be the previousOutput adjusted to the tolerable band, while being aware of positive/negative
//         }
//         else {
//             output = pidInput;
//         }
//         motor.set(output);
//         previousOutput = output;
//     }
// }