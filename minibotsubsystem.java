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
//for reading the encoder
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
//for movement of Sparks
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
//for pigeon
import com.ctre.phoenix.sensors.PigeonIMU;
//to get the values of CAN id's
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
* this subsys includes pigeon
* talons
* sparks
*
*
*
* */
public class minibotsubsystem extends Subsystem {

    public TalonSRX longmotor = new TalonSRX(RobotMap.longmotor);
    public CANSparkMax neo = new CANSparkMax(RobotMap.neo,MotorType.kBrushless);// motor must be set
    public PigeonIMU pidgeon = new PigeonIMU(RobotMap.pidgeon);
    // brushless or brushed or will damage motor

    public minibotsubsystem(){
        longmotor.configFactoryDefault();
        longmotor.setNeutralMode(NeutralMode.Brake);
        //neo.setInputMode(InputMode.can);// needs to be found and set
        neo.setIdleMode(IdleMode.kBrake);
        longmotor.getSensorCollection().setPulseWidthPosition(0, 10);
        longmotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);//the type of sensor in the encoder is asumed
        longmotor.setSelectedSensorPosition(0, 0, 50);
        //PID values for movetolong()
        longmotor.config_kF(0, 0, 50);
        longmotor.config_kP(0, .1, 50);
        longmotor.config_kI(0, .1, 50);
        longmotor.config_kD(0, .1, 50);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        longmotor.set(ControlMode.PercentOutput,0);// set  out to 0 on start to stop motor from moving accidentally on startup

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setneobrakemode(int mode){
        //0 to coast
        //1 to brake
        if(mode ==0){
            neo.setIdleMode(IdleMode.kCoast);
        } else if(mode == 1){
            neo.setIdleMode(IdleMode.kBrake);
        }else{
            System.out.println("setneobrakemode() invalid mode");
        }
    }

    public void setlongmotorbrakemode(int mode){
        //same as above
        if(mode == 0){
            longmotor.setNeutralMode(NeutralMode.Brake);
        } else if(mode ==1){
            longmotor.setNeutralMode(NeutralMode.Coast);
        } else {
            System.out.println("setlongbrakemode invalid mode");
        }

    }

    public void setpowerneomotor(double set){
        neo.set(set);


    }

    public void setpowerlongmotor(double percent){

        longmotor.set(ControlMode.PercentOutput, percent);

    }

    public void printpidgeonvalues(int whichone){
        // yaw pitch and roll

        System.out.println("new pigeon values");
        switch(whichone) {

            case 1:
                double[] ypr = new double[3];
                pidgeon.getYawPitchRoll(ypr);
                System.out.println("pidgeon ypr " + "y " + ypr[0] + " p " + ypr[1] + " r " + ypr[2]);
                break;

            //quaternions
            case 2:
                double[] quaternions = new double[4];
                pidgeon.get6dQuaternion(quaternions);
                System.out.println("quaternions(6d #s) " + "W" + quaternions[0] + "X" + quaternions[1] + "Y " + quaternions[2] + "Z " + quaternions[3]);
                break;

            //acumulated gyro
            case 3:
                double[] accumGyro = new double[3];
                pidgeon.getAccumGyro(accumGyro);
                System.out.println("accumGyro" + "X" + accumGyro[0] + "Y" + accumGyro[1] + "Z" + accumGyro[2]);
                break;

            // biased accel
            case 4:
                short[] biasedAccel = new short[3];
                pidgeon.getBiasedAccelerometer(biasedAccel);
                System.out.println("biased acceleration" + "X" + biasedAccel[0] + "Y" + biasedAccel[1] + "Z" + biasedAccel[2]);
                break;

            //raw gyro
            case 5:
                double[] rawGyro = new double[3];
                pidgeon.getRawGyro(rawGyro);
                System.out.println("raw gyro" + " X" + rawGyro[0] + "Y" + rawGyro[1] + "Z" + rawGyro[2]);
                break;

            //accelerometer angles
            case 6:
                double[] accelAngles = new double[3];
                pidgeon.getAccelerometerAngles(accelAngles);
                System.out.println("acceleration angles" + "X" + accelAngles[0] + "Y" + accelAngles[1] + "Z" + accelAngles[2]);
                break;

            // biased magnetometer
            case 7:
                short[] biasedMagnet = new short[3];
                pidgeon.getBiasedMagnetometer(biasedMagnet);
                System.out.println("biased magnetometer" + "X" + biasedMagnet[0] + "Y" + biasedMagnet[1] + "Z" + biasedMagnet[2]);
                break;

            // raw magnetometer
            case 8:
                short[] rawMagnet = new short[3];
                pidgeon.getRawMagnetometer(rawMagnet);
                System.out.println("rawMagnetomert " + "X" + rawMagnet[0] + "Y" + rawMagnet[1] + "Z" + rawMagnet[2]);
                break;

        }
        System.out.println();


    }

    public void longmotorencoder(){
        System.out.println("longmotor encoder position"+longmotor.getSelectedSensorPosition(0));
        System.out.println("longmotor encoder velocity"+ longmotor.getSelectedSensorVelocity(0));
    }

}