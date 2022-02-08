/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TrajectoryHelper;
import frc.robot.subsystems.LimelightPortal;
import frc.robot.subsystems.TimeOfFlightRange;
import frc.robot.subsystems.Drive.SwerveDriveSubsystem;
import frc.robot.utility.TrajectoryMaker;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AlignToFender extends SequentialCommandGroup {
  /**
   * Creates a new AlignToFender.
   */
  private LimelightPortal limelight;
  private SwerveDriveSubsystem swervedrive; 
  private TimeOfFlightRange sensor;
  TrajectoryMaker move = TrajectoryHelper.createMoveToFender();

  public AlignToFender(LimelightPortal ll, SwerveDriveSubsystem swerveDrive, TimeOfFlightRange sensor) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    super(new AlignAndMoveToLimelight(70, swerveDrive, ll), 
      new AlignAndMoveToLimelight(70, swerveDrive, ll), 
      new TurnToZeroLimelight(0, swerveDrive, ll).withTimeout(0.25),
      new GoToDistanceTimeOfFlight(3, swerveDrive, sensor)
      );

  }
}
