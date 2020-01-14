package frc.robot.input;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap2;
import frc.robot.OI;

public class ElevatorSensors extends SensorBatch {
    //Batch of elevator-related sensors

    private DigitalInput m_lowlimit;
    //private DigitalInput m_highlimit; not needed for this code, but here if you want it

    //Necessary to only have one ElevatorSensors instance, using Singleton pattern
    private Encoder m_enc;
    
    private static ElevatorSensors m_instance = null;
    
    private ElevatorSensors() {
        m_lowlimit = new DigitalInput(IO.kELEV_LOWBOUND_PORT);
        //m_highlimit = new DigitalInput(IO.kELEV_UPBOUND_PORT);
        //encoder set to track radians
        m_enc = new Encoder(IO.kELEV_ENC_CHAN_A, IO.kELEV_ENC_CHAN_B, 
            Constants.kELEV_ENC_REVERSE, Constants.kELEV_DECODE_RATE);
        m_enc.setMaxPeriod(Constants.kELEV_ENC_MAXPERIOD); 
        m_enc.setMinRate(Constants.kELEV_ENC_MINRATE);
        m_enc.setDistancePerPulse(Constants.kELEV_ENC_RADIANS_PER_PULSE);
        m_enc.setSamplesToAverage(5); 
    }


    public static ElevatorSensors getInstance() {
        if (m_instance == null) {
            m_instance = new ElevatorSensors();
        }

        return m_instance;
    }



    @Override
    public HashMap<String, Object> getUpdate() {
        //compute radians (for rotation) to meters (linear movement) by multiplying by drum radius
        double elevHeight = m_enc.getDistance()*Constants.kELEV_DRUM_RADIUS;
       
        //store if lower endstop is triggered
        HashMap<String, Object> senseOutMap = new HashMap<String, Object>();
        senseOutMap.put(Constants.ELEV_LOWBOUND_OUT,!m_lowlimit.get()); //Hall effect is closed w/o magnet
        //senseOutMap.put(Constants.ELEV_HIGHBOUND_OUT,m_highlimit.get()); 
        senseOutMap.put(Constants.ELEV_ENCODER_OUT, elevHeight);

        //SmartDashboard sensor updates to display on DriverStation
        boolean isDown = (boolean)senseOutMap.get(Constants.ELEV_LOWBOUND_OUT);
        double pos = (double)senseOutMap.get(Constants.ELEV_ENCODER_OUT);
        SmartDashboard.putBoolean("Is_Elevator_Down", isDown);
        SmartDashboard.putNumber("Elevator_Encoder_Position", pos);

        return senseOutMap;
    }



}