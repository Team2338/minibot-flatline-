/*
* minibot subystem
* includes two motors one with a spark max
* another one with the talons
* both of which were flashed with the latest firmware (talon v4.22 2019)(Sparks v1.4.0 2019)
* */
/* TODO:
*
*   set power to neo
*   set power to longboimoitor
*/
package frc.robot.subsystems;


//for the movement of talons
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
//for movement of Sparks
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
//the pidgeon imports
import com.ctre.phoenix.sensors.PigeonIMU;
//to get the values of CAN id's
import frc.robot.RobotMap;
// bc it wus neceersury
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class minibotsubsystem extends Subsystem {

    public TalonSRX longmotor = new TalonSRX(RobotMap.longmotor);
    public CANSparkMax neo = new CANSparkMax(RobotMap.neo,MotorType.kBrushless);// motor must be set
    public PigeonIMU pidgeon = new PigeonIMU(RobotMap.pidgeon);
    // brushless or brushed or will damage motor

    public minibotsubsystem(){
        longmotor.setNeutralMode(NeutralMode.Brake);

        //neo.setInputMode(InputMode.can);// needs to be found and set
        neo.setIdleMode(IdleMode.kBrake);

    }



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand()
    {
        longmotor.set(ControlMode.PercentOutput,0);// set  out to 0 on start to stop motor from moving accidentally on startup

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setpowerneomotor(double set){
        neo.set(set);


    }

    public void setpowerlongmotor(double percent){

        longmotor.set(ControlMode.PercentOutput, percent);

    }

    public void printpidgeonvalue(){
        // yaw pitch and roll
        System.out.println("new thing");

        double[] ypr = new double[3];
        pidgeon.getYawPitchRoll(ypr);
        System.out.println("pidgeon ypr "+"y "+ypr[0]+" p "+ypr[1]+" r "+ypr[2]);


        //quaternions
        double[] quaternions = new double[4];
        pidgeon.get6dQuaternion(quaternions);
        System.out.println("quaternions(6d #s) " +"W"+ quaternions[0] + "X"+quaternions[1] +"Y "+quaternions[2]+"Z "+quaternions[3]);



        //acumulated gyro
        double[] accumGyro = new double[3];
        pidgeon.getAccumGyro(accumGyro);
        System.out.println("accumGyro" +"X"+accumGyro[0]+"Y"+accumGyro[1]+"Z"+accumGyro[2]);

        // biased accel
        short[] biasedAccel = new short[3];
        pidgeon.getBiasedAccelerometer(biasedAccel);
        System.out.println("biased acceleration"+ "X"+biasedAccel[0]+"Y"+biasedAccel[1]+"Z"+biasedAccel[2]);

        //raw gyro
        double[] rawGyro = new double[3];
        pidgeon.getRawGyro(rawGyro);
        System.out.println("raw gyro" +" X" + rawGyro[0]+ "Y" + rawGyro[1] +"Z"+ rawGyro[2] );

        //accelerometer angles
        double[] accelAngles = new double[3];
        pidgeon.getAccelerometerAngles(accelAngles);
        System.out.println("acceleration angles"+ "X"+accelAngles[0]+"Y"+accelAngles[1]+"Z"+accelAngles[2]);

        // biased magnetometer
        short[] biasedMagnet = new short[3];
        pidgeon.getBiasedMagnetometer(biasedMagnet);
        System.out.println("biased magnetometer"+ "X"+biasedMagnet[0]+"Y"+biasedMagnet[1]+"Z"+biasedMagnet[2]);

        // raw magnetometer
        short[] rawMagnet = new short[3];
        pidgeon.getRawMagnetometer(rawMagnet);
        System.out.println("rawMagnetomert "+ "X"+rawMagnet[0]+"Y"+rawMagnet[1]+"Z"+rawMagnet[2]);

        System.out.println();


    }

}
