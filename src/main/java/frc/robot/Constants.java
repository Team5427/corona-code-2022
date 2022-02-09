/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //****** NUMBERS *******/
    public static final double DRIVETRAIN_WHEEL_DIAMETER = 4; //in inches
    public static final double DISTANCE_PER_PULSE = (Math.PI * DRIVETRAIN_WHEEL_DIAMETER/256)/12.63;

    //Joystick buttons

    //Speeds

    /*****************Motor ports*****************/
    public static final int LEFT_TOP_MOTOR = 7; 
	public static final int LEFT_BOTTOM_MOTOR = 8;
	public static final int RIGHT_TOP_MOTOR = 10;
    public static final int RIGHT_BOTTOM_MOTOR = 11;

    /*******************Sensors*******************/
    public static final int dt_right_1 = 4;
    public static final int dt_right_2 = 5;
    public static final int dt_left_1 = 6;
    public static final int dt_left_2 = 7;
    public static final double AUTONOMOUS_DRIVE_SPEED = .5;
}
