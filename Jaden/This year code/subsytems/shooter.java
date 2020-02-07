package frc.robot.subsystems;


public class Shooter extends subsystems;

public class Shooter extends Subsystems {
    public Spark leftMotor;
    public Spark rightMotor;


    public static Shooter() {
        leftMotor = new Spark(RobotMap.leftShooter.value);
        rightMotor = new Spark(RobotMap.rightShooter.value);
        shooter = new DifferentialDrive(leftMotor, rightMotor);
        
      }
    //power for specific motors is 1 - -1

   
    public static void bigForward() {
        leftMotor.set(1);
        rightMotor.set(1);
    }
//replace task with function

public static void smallForward() {
    leftMotor.set(0.5);
    rightMotor.set(0.5);
}
}
public static void bigLeft() {
    leftMotor.set(1);
    rightMotor.set(0);
}
  
}
public static void smallLeft() {
    leftMotor.set(0.5);
    rightMotor.set(0);
}
  
}
public static void bigRight() {
    leftMotor.set(0);
    rightMotor.set(1);
}
  
}
public static void smallForward() {
    leftMotor.set(0);
    rightMotor.set(0.5);
}
  
}
      @Override
      public static void initDefaultCommand() {
      }
    }