/*
 Copyright (c) 2018 FIRST. All Rights Reserved.
 Open Source Software - may be modified and shared by FRC teams. The code
 must be accompanied by the FIRST BSD license file in the root directory of
 the project.                                                               */
//errors and solutions
/*
* .\gradlew failed long ass error and unsupported class file 57
* javac and java bytecode versions both must be 11 gradle not compatible with latest 13
* */
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.lib.drivers.Limelight;
import frc.robot.commands.*;
import frc.robot.commands.minibotsautos.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */


public class Robot extends TimedRobot
{
    public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static minibotsubsystem minibotsubsys = new minibotsubsystem();

    public static Limelight limelight;
    public static OI oi;
    
    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();
    public static Scheduler scheduler;
    
    /**
     * This ExampleCommand.java method is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        chooser.setDefaultOption("minibotauto(default)", new minibotauto());
        // chooser.addOption("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        limelight = new Limelight();
        //limelight.setPipeline(0);// sets second usb webcam to be side by side on display to the og display
        //above disabled bc we have no second webcam

        scheduler = Scheduler.getInstance();
    }
  
    /**
     * This method is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic methods, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        
    }
    
    /**
     * This method is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        
    }
    
    @Override
    public void disabledPeriodic() {
        scheduler.getInstance().run();
    }
    
    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        //limelight.setPipeline(0);// sets second usb webcam to be side by side on display to the og display
        //above disabled bc we have no second webcam
        scheduler.add(new minibotauto());

    
        // schedule the autonomous command (example)
    }
  
    /**
     * This method is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        /*
        System.out.println("attempted to get has target"+limelight.hasTarget());
        System.out.println("tx"+limelight.getXOffset());
        System.out.println("ty"+limelight.getYOffset());
        */
        scheduler.getInstance().run();
    }
  
    @Override
    public void teleopInit()
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
        {
            autonomousCommand.cancel();
        }
    }
  
    /**
     * This method is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic()
    {
        scheduler.getInstance().run();
    }
  
    /**
     * This method is called periodically during test mode.
     */
    @Override
    public void testPeriodic()
    {
        
    }
}
