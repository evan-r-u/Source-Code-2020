// package frc.robot.input;

// import java.util.HashMap;

// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.CounterBase.EncodingType;
// import frc.robot.RobotMap;
// import frc.robot.OI;

// public class DriveSensors extends SensorBatch {

//     //Encoders were not installed in the 2019 drivetrain, but this is example code in case you want to install some in the future.

//     private Encoder m_leftEnc;
//     private Encoder m_rightEnc;
    
//     private static DriveSensors m_instance = null;

//     private DriveSensors() {
//         //setting encoder objects to represent rotations in radians. 

//         // m_leftEnc = new Encoder(IO.kDRIVE_LEFT_ENC_CHAN_A, IO.kDRIVE_LEFT_ENC_CHAN_B, 
//         //     Constants.kDRIVE_LEFTENC_REVERSE, Constants.kDRIVE_ENC_DECODE_RATE);
//         // m_leftEnc.setMaxPeriod(Constants.kDRIVE_ENC_MAXPERIOD);
//         // m_leftEnc.setMinRate(Constants.kDRIVE_ENC_MINRATE);
//         // m_leftEnc.setDistancePerPulse(Constants.kDRIVE_ENC_RADIANS_PER_PULSE);
//         // m_leftEnc.setSamplesToAverage(7);

//         // m_rightEnc = new Encoder(IO.kDRIVE_RIGHT_ENC_CHAN_A, IO.kDRIVE_RIGHT_ENC_CHAN_B,
//         //     Constants.kDRIVE_RIGHTENC_REVERSE, Constants.kDRIVE_ENC_DECODE_RATE);
//         // m_rightEnc.setMaxPeriod(Constants.kDRIVE_ENC_MAXPERIOD);
//         // m_rightEnc.setMinRate(Constants.kDRIVE_ENC_MINRATE);
//         // m_rightEnc.setDistancePerPulse(Constants.kDRIVE_ENC_RADIANS_PER_PULSE);
//         // m_rightEnc.setSamplesToAverage(7);
//     }

//     public static DriveSensors getInstance() {
//         if (m_instance == null) {
//             m_instance = new DriveSensors();
//         }

//         return m_instance;
//     }
   
   
//     @Override
//     public HashMap<String, Object> getUpdate() {
//         //rate will be given in rad/sec, so you must multiply by wheel radius to convert to m/s

//         // double leftVel = m_leftEnc.getRate()*Constants.kDRIVE_WHEEL_RADIUS;
//         // double rightVel = m_rightEnc.getRate()*Constants.kDRIVE_WHEEL_RADIUS;
        
//         // HashMap<String, Object> senseOutMap = new HashMap<String, Object>();
//         // senseOutMap.put(Constants.DRIVE_LEFT_ENC_OUT, leftVel);
//         // senseOutMap.put(Constants.DRIVE_RIGHT_ENC_OUT, rightVel);

//         // return senseOutMap;
//         return null;
//     }




// }


// public class Constants {
//    /*INPUT INTERPRETATION CONSTANTS*/
//    //Elevator
//    public static final double kELEV_LOW_HEIGHT = 0.02; // in m
//    public static final double kELEV_MID_HEIGHT = 0.3556; // in m
//    public static final double kELEV_HIGH_HEIGHT = 0.6096; // in m
   
//     /*ELEVATOR CONSTANTS */
//     //Encoder constants
//     public static final double kELEV_ENC_RADIANS_PER_PULSE = 2*3.14/1024; // in rad/tick
//     public static final double kELEV_ENC_MINRATE = 6*3.14; // in rad/s = 3 revs/s
//     public static final double kELEV_ENC_MAXPERIOD = 1.0; // in s
//     public static final boolean kELEV_ENC_REVERSE = false;
//     public static final Encoder.EncodingType kELEV_DECODE_RATE = Encoder.EncodingType.k4X; // higher precision in calculation, more prone to jitter
//     //Elevator modelling constants
//     public static final double kELEV_DRUM_RADIUS = 0.02032; // in m
//     //Elevator control constants                                    
//     public static final double kZERO_SPEED = 0.025; //in m/s
//     public static final double kELEV_MIN_HEIGHT = 0.02; //determine after measuring elevator in  meters
//     public static final double kELEV_MAX_HEIGHT = 0.7; // in m. this is nowhere near the top of the elevator but this is to stay safe within mechanical bounds
//     public static final double kELEV_Kp = 300; 
//     public static final double kELEV_Kd = 0; 
//     public static final double kELEV_Kstall = 1.5; // in V
    

//     /*DRIVETRAIN CONSTANTS*/
//     //Encoder constants -- 2019 robot doesn't have encoders, so these are sample values. 
//   /*public static final double kDRIVE_ENC_RADIANS_PER_PULSE = 2*3.14/512; //Check, assume encoders have 512 pulse/rev
//     public static final double kDRIVE_ENC_MAXPERIOD = 1.0;
//     public static final double kDRIVE_ENC_MINRATE = 6*3.14;
//     public static final Encoder.EncodingType kDRIVE_ENC_DECODE_RATE = Encoder.EncodingType.k2X;
//     public static final boolean kDRIVE_LEFTENC_REVERSE = false;
//     public static final boolean kDRIVE_RIGHTENC_REVERSE = true; */
    
//     //Drivetrain modelling constants
//     public static final double kDRIVE_WHEEL_RADIUS = 0.0762; // in m
//     //Drivetrain control constants
//     public static final double kDRIVE_MAX_SPEED =  8; //in m/s
//     public static final double kDRIVE_MAX_ACCEL = 16; // in m/s^2
//     public static final double kDRIVE_LEFT_Kf = 12.0/11.0;  // V*s/m - I didn't have enough time to measure these, so these are guesses. If you actually are using this
//     public static final double kDRIVE_RIGHT_Kf = 12.0/11.0; //          in competition, then measure!
//     public static final double kDRIVE_DEADBAND = 0.025; // in m/s
    
//     /*TRANSFER-HASHMAP KEYS */
//         //These are keys for the data transfer tables
//     //Drivetrain input keys
//     public static final String DRIVE_LEFT_SETPOINT = "drive left setpoint";
//     public static final String DRIVE_RIGHT_SETPOINT = "drive right setpoint";
//     //Drivetrain sensor reading keys
//      //public static final String DRIVE_LEFT_ENC_OUT = "drivetrain left encoder";
//      //public static final String DRIVE_RIGHT_ENC_OUT = "drivetrain right encoder";
//     //Drivetrain controller output keys
//     public static final String DRIVE_LEFT_VOLTAGE = "drivetrain left motor voltage";
//     public static final String DRIVE_RIGHT_VOLTAGE = "drivetrain right motor voltage";

//     //Elevator input keys
//     public static final String ELEV_ZERO = "zero elevator in";
//     public static final String ELEV_SETPOINT = "elevator setpoint";
//     //Elevator sensor reading keys
//     public static final String ELEV_LOWBOUND_OUT = "elevator lower bound switch";
//      //public static final String ELEV_HIGHBOUND_OUT = "elevator upper bound switch";
//     public static final String ELEV_ENCODER_OUT = "elevator encoder position";
//     //Elevator controller output keys
//     public static final String ELEV_VOLTAGE = "elevator motor voltage";
   


// }