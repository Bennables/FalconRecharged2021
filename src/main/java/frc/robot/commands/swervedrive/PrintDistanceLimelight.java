/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swervedrive;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightPortal;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PrintDistanceLimelight extends InstantCommand {
  LimelightPortal llGet;
  public PrintDistanceLimelight(LimelightPortal LL) {
    llGet = LL;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double llDistance = llGet.getDistance();
    double llX = llGet.getX();
    SmartDashboard.putNumber("limelight-distance", llDistance);
    SmartDashboard.putNumber("limelight-angle", llX);
    // String widgType = Shuffleboard.getWidgetName("limelight-test");
  }
}
