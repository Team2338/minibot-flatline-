/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*  PID summary
 *       proportionality
 *           power to motor is directly proportional to distance left to go
 *       Integral
 *           S distancefromtarget dtime (should increase power if distance from target does not change)
 *       FeedForward
 *           Y intercept of Power set to motor graph, used to overcome Fsf,Fsk or Fg
 *           can be set differently depending on which direction you move
 *       Derivative
 *           d distancefromtarget/ d time  if change in distance to fast it slows vvsa
 *
 * */
package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */

public class movetolong extends Command
{
    public movetolong()
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.exampleSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize()
    {
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute()
    {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished()
    { 
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end()
    {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted()
    {
        
    }
}
