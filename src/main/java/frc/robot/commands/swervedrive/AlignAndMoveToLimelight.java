/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DaphneTwoConstants;
import frc.robot.subsystems.LimelightPortal;
import frc.robot.subsystems.Drive.SwerveDriveSubsystem;

public class AlignAndMoveToLimelight extends CommandBase {
  /**
   * Creates a new AlignAndMoveToLimelight.
   */
  private LimelightPortal limelight;
  private SwerveDriveSubsystem swervedrive;
  private double targetDistance;

  /* distance and angle constants*/

  public AlignAndMoveToLimelight(double distanceInches, SwerveDriveSubsystem drive, LimelightPortal ll) {
    // Use addRequirements() here to declare subsystem dependencies.
    targetDistance = distanceInches; 
    /* height of the target affects whatever target distance we have
    take that into account before setting it in the container class*/
    swervedrive = drive;
    limelight = ll; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double dx = limelight.getX(); //current x angle
    double distance = limelight.getDistance(); //current distance away from target

    double speed = 0.0; //the variable indicating how far swervedrive has to go
    double turn = 0.0;

    double dError = targetDistance - distance; //calculates how far robot is from target
    double sign = dError > 0?1:-1; //figuring if it's negative or not
    dError = Math.abs(dError);

    double aError = Math.abs(dx); //calculates how far off the angle of the aim is
    double aSign = dx > 0?1:-1; //figuring if it's negative or not

    if(dError > DaphneTwoConstants.STAGE_1_DISTANCE) //speed move scale from a 1 to -1
    {
      speed = DaphneTwoConstants.STAGE_1_SPEED;
    }
    else if(dError > DaphneTwoConstants.STAGE_2_DISTANCE)
    {
      speed = DaphneTwoConstants.STAGE_2_SPEED;
    }
    else if(dError > DaphneTwoConstants.STAGE_3_SPEED)
    {
      speed = DaphneTwoConstants.STAGE_3_SPEED;
    }
    else if(dError > DaphneTwoConstants.STAGE_4_SPEED) 
    {
      speed = DaphneTwoConstants.STAGE_4_SPEED;
    }
    else 
    {
      speed = DaphneTwoConstants.STAGE_5_SPEED;
    }


    if(aError > DaphneTwoConstants.TURN_CAP) 
    {
      turn = 0.75;
    }
    // else if(aError < 0.4) 
    // {
    //   turn = 0;
    // }
    else 
    {
      turn = aError / 30;
    }

    swervedrive.holonomicDrive(speed * sign, 0, -turn * aSign); //make swerdrive move

    SmartDashboard.putNumber("limelight-test-distance", distance); 
    SmartDashboard.putNumber("limelight-test-angle", dx); 
    //printing into smartdashboard every instance, for test purposes
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(limelight.getDistance()-targetDistance) < 5 && Math.abs(limelight.getX()) < 1 || !limelight.foundTarget()) 
    //tolerance values: 5 inches and 1 radian(double check if units are correct)
    {
      if(!limelight.foundTarget())
      {
        System.out.println("finished not found");
      }
      else
      {
        System.out.println("finished found");
      }
      return true;
    }
    else
    {
      return false;
    }
  }
}
