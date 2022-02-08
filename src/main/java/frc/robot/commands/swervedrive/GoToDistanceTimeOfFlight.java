/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.TimeOfFlightRange;
import frc.robot.subsystems.Drive.SwerveDriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GoToDistanceTimeOfFlight extends ProfiledPIDCommand {
  /**
   * Creates a new GoToDistanceTimeOfFlight.
   */
  TimeOfFlightRange sensorCheck;

  public GoToDistanceTimeOfFlight(double distanceInches, SwerveDriveSubsystem drive, TimeOfFlightRange sensor) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(Constants.DISTANCE_PID_P,Constants.DISTANCE_PID_I, Constants.DISTANCE_PID_D, //need to tune this better
            new TrapezoidProfile.Constraints(Constants.MAX_DISTANCE_VELOCITY,Constants.MAX_DISTANCE_ACCELERATION)),
        // This should return the measurement
        sensor::getDistanceSensor,
        // This should return the goal (can also be a constant)
        distanceInches,
        // This uses the output
        (output, setpoint) -> drive.holonomicDrive(output, 0, 0),
        drive);
    sensorCheck = sensor;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return sensorCheck.getDistanceSensor() < 4;
  }
}
