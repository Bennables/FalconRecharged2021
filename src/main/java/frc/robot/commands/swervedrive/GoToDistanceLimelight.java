/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import frc.robot.subsystems.LimelightPortal;
import frc.robot.subsystems.Drive.*;
import frc.robot.Constants;

//JMH

/**
 * A command that will turn the robot to the specified angle.
 */
public class GoToDistanceLimelight extends ProfiledPIDCommand {
    private SwerveDriveSubsystem swerveDriveSubsystem;
    private LimelightPortal limeL;
    TrapezoidProfile.Constraints rampUpDown = new TrapezoidProfile.Constraints(10, 5);

    /**
     * Turns to robot to the specified angle.
     *
     * @param targetAngleDegrees The angle to turn to
     * @param drive              The drive subsystem to use
     * 
     * default distanceInches should be how big the bumper is
     */
    public GoToDistanceLimelight(double distanceInches, SwerveDriveSubsystem drive, LimelightPortal ll) {
    super(
        new ProfiledPIDController(Constants.DISTANCE_PID_P,Constants.DISTANCE_PID_I, Constants.DISTANCE_PID_D, //need to tune this better
            new TrapezoidProfile.Constraints(Constants.MAX_DISTANCE_VELOCITY,Constants.MAX_DISTANCE_ACCELERATION)),
        
        // Close loop on heading
        ll::getDistance,
        // Set reference to target
        distanceInches,  
        // Pipe output to turn branchrobot
        (output,setpoint) -> drive.holonomicDrive(output, 0, 0),
        // Require the drive
        drive);

    // Set the controller to be continuous (because it is an angle controller)
    //getController().enableContinuousInput(-180, 180);
    swerveDriveSubsystem = drive;
    limeL = ll;
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(Constants.DISTANCE_TOLERANCE, 10);
    System.out.println("***********" + distanceInches + "**********");
  }


  @Override
  public void execute() {
   
    super.execute();
    System.out.println("robot distance: " 
        + limeL.getDistance());
  }
  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atGoal();
  }


}