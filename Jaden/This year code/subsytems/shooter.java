package frc.robot.subsystems;


public class Shooter extends subsystems;

public class Shooter extends Subsystems {
    public Spark leftMotor;
    public Spark rightMotor;
    public DifferentialDrive shooter;

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

    task smallForward() {
        Motor(rightMotor) = 0.5;
        Motor(leftMotor) = 0.5;
}
task bigLeft() {
    Motor(rightMotor) = 1;
    Motor(leftMotor) = 0;
  
}
task smalllLeft() {
    Motor(rightMotor) = 0.5;
    Motor(leftMotor) = 0;
  
}
task bigright() {
    Motor(rightMotor) = 0;
    Motor(leftMoror) = 1;
  
}
task smallright() {
    Motor(rightMotor) = 0;
    Motor(leftMoror) = 0.5;
  
}
      @Override
      public void initDefaultCommand() {
      }
    }