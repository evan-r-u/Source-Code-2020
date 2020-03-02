package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;


public class Magazine extends Subsystem {
    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a 
     * parameter. The device will be automatically initialized with default 
     * parameters.
     */

    public static int ballCount; 
    public Spark magazineSpark;
    private I2C.Port i2cPort;
    public static int waitCount;
    public static double tempIndexDelay;
    public Encoder magazineEncoder;

    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a 
     * parameter. The device will be automatically initialized with default 
     * parameters.
     */
    private ColorSensorV3 m_colorSensor;


    public Magazine() {
        i2cPort = I2C.Port.kOnboard;
        m_colorSensor = new ColorSensorV3(i2cPort);
        magazineSpark = new Spark(RobotMap.magazineMotor);
        // ballCount = 0;
        // waitCount = 0;
        magazineEncoder = new Encoder(RobotMap.magazineEncoderA, RobotMap.magazineEncoderB, RobotMap.magazineEncoder_Reverse, Encoder.EncodingType.k2X);
        
        magazineEncoder.reset();
        magazineEncoder.setDistancePerPulse(1./256.);
        magazineEncoder.setMaxPeriod(RobotMap.magazineEncoder_MaxPeriod);
        magazineEncoder.setMinRate(RobotMap.magazineEncoder_MinRate);
        magazineEncoder.setDistancePerPulse(RobotMap.magazineEncoder_RadiansPerPulse);
        magazineEncoder.setSamplesToAverage(7);

    }

    public void checkColor() {
        /**
         * The method GetColor() returns a normalized color value from the sensor and can be
         * useful if outputting the color to an RGB LED or similar. To
         * read the raw color, use GetRawColor().
         * 
         * The color sensor works best when within a few inches from an object in
         * well lit conditions (the built in LED is a big help here!). The farther
         * an object is the more light from the surroundings will bleed into the 
         * measurements and make it difficult to accurately determine its color.
         */

        SmartDashboard.putNumber("Number of Balls", RobotMap.numberOfBalls);
        System.out.println("Number of Balls");
        System.out.println(RobotMap.numberOfBalls);

        Color detectedColor = m_colorSensor.getColor();

        /**
         * The sensor returns a raw IR value of the infrared light detected.
         */
        double IR = m_colorSensor.getIR();
    
        /**
         * Open Smart Dashboard or Shuffleboard to see the color detected by the 
         * sensor.
         */
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("IR", IR);

        /**
         * In addition to RGB IR values, the color sensor can also return an 
         * infrared proximity value. The chip contains an IR led which will emit
         * IR pulses and measure the intensity of the return. When an object is 
         * close the value of the proximity will be large (max 2047 with default
         * settings) and will approach zero when the object is far away.
         * 
         * Proximity can be used to roughly approximate the distance of an object
         * or provide a threshold for when an object is close enough to provide
         * accurate color values.
         */
        int proximity = m_colorSensor.getProximity();

        SmartDashboard.putNumber("Proximity", proximity);
        // Detects color so that it only runs if ball is yellow
        // If ball is there
        if (detectedColor.blue <= 0.15) {

            // Uses magazine encoder
            if (RobotMap.rotationMode) {
                rotateMagazine();
                RobotMap.numberOfBalls++;
            }


            // Uses timing delay
            else {
                if (RobotMap.numberOfBalls == 0) {
                    tempIndexDelay = RobotMap.indexDelayAdjusted * 0.65;
                }
                if (RobotMap.numberOfBalls == 1) {
                    tempIndexDelay = RobotMap.indexDelayAdjusted * 0.85;
                    // intake.intakeRoller.set(RobotMap.intakeSpeedAdjusted * 1.2);
                }
                if (RobotMap.numberOfBalls == 2) {
                    tempIndexDelay = RobotMap.indexDelayAdjusted * 0.85;
                }
                if (RobotMap.numberOfBalls == 3) {
                    tempIndexDelay = RobotMap.indexDelayAdjusted * 0.85;
                }
                // RobotMap.numberOfBalls++;
                magazineSpark.set(RobotMap.magazinePower);
                Timer.delay(RobotMap.magazinePower);
                // RobotMap.numberOfBalls++;
                // magazine.magazineSpark.set(0.5);
                // Timer.delay(RobotMap.indexDelayAdjusted);
                magazineSpark.set(0.0);
                // RobotMap.numberOfBalls++;
                }
            }

            // // OLD CODE 

            // if (RobotMap.numberOfBalls == 2) {
                //   // RobotMap.numberOfBalls++;
                //   RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed / 2;
                // }
            // if (RobotMap.numberOfBalls == 3) {
            //     // RobotMap.numberOfBalls++;
            //     RobotMap.intakeSpeedAdjusted = RobotMap.intakeSpeed / 2;
            // }
            // else if (RobotMap.numberOfBalls == 4) {
            //     Timer.delay(0.1);
            //     RobotMap.intakeSpeedAdjusted = 0.0;
            // }
            // else {
            //     if (RobotMap.numberOfBalls == 1) {
            //         tempIndexDelay = RobotMap.indexDelayAdjusted * 0.8;
            //     }
            //     else {
            //         tempIndexDelay = RobotMap.indexDelayAdjusted;
            //     }
            //     // RobotMap.numberOfBalls++;
            //     magazineSpark.set(0.5);
            //     Timer.delay(tempIndexDelay);
            //     magazineSpark.set(0.0);
            // }
        }
        


            // waitCount++;

            // SmartDashboard.putNumber("waitCount", waitCount);

            // while()

            // if (waitCount >= 75) {

            //     magazineSpark.set(0.0);
            //     waitCount = 0;
            // }
            // else {
            //     runMagazine();
            // }
    

        // waitCount = 0;
            
        // else {
        //     magazineSpark.set(0.0);
        // }

        // magazineSpark.set(0.6);


    public void runMagazine() {
        //checks that slot is open
        // if (ballCount < 5) {
        //run motor for ball to go up
        magazineSpark.set(1);

        //ball count goes up by 1
        // ballCount++;
        }    
    
    public void shootBall() {
        // if (RobotMap.numberOfBalls > 0) {
        //     if (RobotMap.numberOfBalls <= 2 && RobotMap.delayIsAdjusted) {
        //     RobotMap.indexDelayAdjusted += 0.12;
        //     RobotMap.delayIsAdjusted = false;
            
        //     }
        // }

        // FIX THIS ADJUSTMENT - BASED ON "UNGRADUATED" VALUES
        magazineSpark.set(RobotMap.magazinePower);
        Timer.delay(RobotMap.indexDelayAdjusted);
        magazineSpark.set(0.0);
        RobotMap.numberOfBalls--;

    }

    public void rotateMagazine() {
        // Rotates the magazine until the rotation distance is achieved
        System.out.println(magazineEncoder.getDistance());
        if (magazineEncoder.getDistance() < RobotMap.magazineRotationDistance) {
            magazineSpark.set(RobotMap.magazinePower);
        } else {
            magazineEncoder.reset();
        }
        
    }

    @Override
    public void initDefaultCommand() {

    }
}