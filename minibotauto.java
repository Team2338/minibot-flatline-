/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.minibotsautos;


import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class minibotauto extends CommandGroup {

    //String the commands for your test auto here
    public minibotauto() {
        addSequential(new readpigeon());
    }

    @Override
    protected void initialize() {



    }
}