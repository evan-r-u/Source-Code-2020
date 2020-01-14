package frc.robot.input;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.RobotMap2;
import frc.robot.OI;

public class DriveSensors extends SensorBatch {

    //Encoders were not installed in the 2019 drivetrain, but this is example code in case you want to install some in the future.

    private Encoder m_leftEnc;
    private Encoder m_rightEnc;
    
    private static DriveSensors m_instance = null;

    private DriveSensors() {
        //setting encoder objects to represent rotations in radians. 

        // m_leftEnc = new Encoder(IO.kDRIVE_LEFT_ENC_CHAN_A, IO.kDRIVE_LEFT_ENC_CHAN_B, 
        //     Constants.kDRIVE_LEFTENC_REVERSE, Constants.kDRIVE_ENC_DECODE_RATE);
        // m_leftEnc.setMaxPeriod(Constants.kDRIVE_ENC_MAXPERIOD);
        // m_leftEnc.setMinRate(Constants.kDRIVE_ENC_MINRATE);
        // m_leftEnc.setDistancePerPulse(Constants.kDRIVE_ENC_RADIANS_PER_PULSE);
        // m_leftEnc.setSamplesToAverage(7);

        // m_rightEnc = new Encoder(IO.kDRIVE_RIGHT_ENC_CHAN_A, IO.kDRIVE_RIGHT_ENC_CHAN_B,
        //     Constants.kDRIVE_RIGHTENC_REVERSE, Constants.kDRIVE_ENC_DECODE_RATE);
        // m_rightEnc.setMaxPeriod(Constants.kDRIVE_ENC_MAXPERIOD);
        // m_rightEnc.setMinRate(Constants.kDRIVE_ENC_MINRATE);
        // m_rightEnc.setDistancePerPulse(Constants.kDRIVE_ENC_RADIANS_PER_PULSE);
        // m_rightEnc.setSamplesToAverage(7);
    }

    public static DriveSensors getInstance() {
        if (m_instance == null) {
            m_instance = new DriveSensors();
        }

        return m_instance;
    }
   
   
    @Override
    public HashMap<String, Object> getUpdate() {
        //rate will be given in rad/sec, so you must multiply by wheel radius to convert to m/s

        // double leftVel = m_leftEnc.getRate()*Constants.kDRIVE_WHEEL_RADIUS;
        // double rightVel = m_rightEnc.getRate()*Constants.kDRIVE_WHEEL_RADIUS;
        
        // HashMap<String, Object> senseOutMap = new HashMap<String, Object>();
        // senseOutMap.put(Constants.DRIVE_LEFT_ENC_OUT, leftVel);
        // senseOutMap.put(Constants.DRIVE_RIGHT_ENC_OUT, rightVel);

        // return senseOutMap;
        return null;
    }




}