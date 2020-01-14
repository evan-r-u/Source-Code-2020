package frc.robot.input;

import java.util.HashMap;

/* A SensorBatch is a group of related sensors needed to support a subsystem or system of subsystems. 
The getUpdate() method is to used to poll current sensor readings and convert the outputs into a form that
can be ingested by the controllers (i.e. if your setpoint of an elevator is in elevator height, you want to transform your sensor)
output into elevator height.*/
public abstract class SensorBatch {

    public abstract HashMap<String,Object> getUpdate(); 
    
}