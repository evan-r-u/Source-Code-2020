package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;


public class Magazine extends Subsystem {
    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a 
     * parameter. The device will be automatically initialized with default 
     * parameters.
     */

    public static int ballCount; 
    public static Spark magazineSpark;
    private I2C.Port i2cPort;
    public static int waitCount;

    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a 
     * parameter. The device will be automatically initialized with default 
     * parameters.
     */
    private ColorSensorV3 m_colorSensor;


    public Magazine() {
        i2cPort = I2C.Port.kOnboard;
        m_colorSensor = new ColorSensorV3(i2cPort);
        magazineSpark = new Spark(RobotMap.magazineMotor.value);
        ballCount = 0;
        waitCount = 0;

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
        // detects color so that it only runs if ball is yellow
        //R = between 240 and 260
        //G = between 190 and 210
        //B = between 0 and 10
        // if(detectedColor.red >= 240 && detectedColor.red <= 260 && detectedColor.green >= 190 && detectedColor.green <= 210 && detectedColor.blue >= 0 && detectedColor.blue <= 10){
        
        // if yellow
        if (detectedColor.blue <= 0.15) {
            runMagazine();
            Timer.delay(4);
            magazineSpark.set(0.0);


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
        }

        // waitCount = 0;
            
        // else {
        //     magazineSpark.set(0.0);
        // }

        // magazineSpark.set(0.6);


    }
    public static void runMagazine() {
        //checks that slot is open
        // if (ballCount < 5) {
        //run motor for ball to go up
        magazineSpark.set(0.4);

        //ball count goes up by 1
        ballCount++;
        }    
    
    @Override
    public void initDefaultCommand() {

    }
}