/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Puncher;
import frc.robot.OI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static Intake intake;
  public static Lifter lifter;
  public static Puncher puncher;
 
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // This is called once when the robot code initializes
    drivetrain = new Drivetrain();
    intake = new Intake();
    lifter = new Lifter();
    puncher = new Puncher();
    oi = new OI();
    }

  @Override
  public void robotPeriodic() {
    // This is called every period regardless of mode
  }

  @Override
  public void autonomousInit() {
    // This is called once when the robot first enters autonomous mode
  }

  @Override
  public void autonomousPeriodic() {
    // This is called periodically while the robot is in autonomous mode
  }

  @Override
  public void teleopInit() {
    // This is called once when the robot first enters teleoperated mode
  }

  @Override
  public void teleopPeriodic() {
    // This is called periodically while the robot is in teleopreated mode
    Scheduler.getInstance().run();
    updateToggle();

    /*Controller 1*/

    lifter.Lifter1.set(oi.XboxController1.getY(Hand.kRight));
    lifter.Lifter2.set(oi.XboxController1.getY(Hand.kLeft));
        // Pivot Control
        intake.intakePivot.set(oi.XboxController1.getTriggerAxis(Hand.kRight)-oi.XboxController1.getTriggerAxis(Hand.kLeft));
    
    // Punch Control
    if (oi.toggleOn1) {
      // Punch
      puncher.punchBackward.set(false);
      puncher.punchForward.set(true);}                                           else{
      // Retract punch
      puncher.punchForward.set(false);
      puncher.punchBackward.set(true);
    }

    
    /*Controller 2*/


    // Intake Roller
    intake.intakeRoller.set(oi.XboxController2.getTriggerAxis(Hand.kRight)-oi.XboxController2.getTriggerAxis(Hand.kLeft));
    

    //Tank Drive
    drivetrain.drivetrain.tankDrive(RobotMap2.power.value * oi.XboxController2.getY(Hand.kLeft), RobotMap2.power.value * oi.XboxController2.getX(Hand.kRight));
    

    if (oi.toggleOn2) {
      // Punch
      puncher.punchBackward.set(false);
      puncher.punchForward.set(true);
    } else{
      // Retract punch
      puncher.punchForward.set(false);
      puncher.punchBackward.set(true);
    }
  }


	private void updateToggle() {
		 if(oi.XboxController1.getRawButton(1)){
        if(!oi.togglePressed1){
            oi.toggleOn1 = !oi.toggleOn1;
            oi.togglePressed1 = true;
        }
        }else{
            oi.togglePressed1 = false;
        }
    
      if(oi.XboxController2.getRawButton(1)){
        if(!oi.togglePressed2){
            oi.toggleOn2 = !oi.toggleOn2;
            oi.togglePressed2 = true;
        }
        }else{
            oi.togglePressed2 = false;
        }
	}

  @Override
  public void testInit() {
    // This is called once when the robot enters test mode
  }

  @Override
  public void testPeriodic() {
    // This is called periodically while the robot is in test mode
  }

}
