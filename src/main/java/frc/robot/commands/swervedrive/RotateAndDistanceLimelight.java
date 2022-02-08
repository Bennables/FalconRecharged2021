/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LimelightPortal;
import frc.robot.subsystems.Drive.SwerveDriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RotateAndDistanceLimelight extends SequentialCommandGroup {
  /**
   * Creates a new RotateAndDistanceLimelight.
   */
  public RotateAndDistanceLimelight(double distanceInches, SwerveDriveSubsystem drive, LimelightPortal ll) 
  // the double distanceInches is unnecessary
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new TurnToZeroLimelight(0, drive, ll), 
      new GoToDistanceLimelight(59, drive, ll), 
      new TurnToZeroLimelight(0, drive, ll));

  }
}
