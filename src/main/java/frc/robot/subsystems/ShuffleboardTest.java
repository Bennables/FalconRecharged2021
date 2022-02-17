/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.commands.swervedrive.PrintDistanceLimelight;
// import frc.robot.commands.swervedrive.ShuffleLimelightTest;
import frc.robot.commands.swervedrive.PrintTest;

public class ShuffleboardTest extends SubsystemBase {
  /**
   * Creates a new ShuffleboardTest.
   */
  // ShuffleboardTab tab = Shuffleboard.getTab("test");
  private ShuffleboardTab llTab = Shuffleboard.getTab("limelight LED");
  NetworkTableEntry llMode = llTab.add("led Mode", 0).getEntry();
  NetworkTableEntry turnTol = llTab.add("turn tolderance", Constants.TURN_TOLERANCE).getEntry();
  NetworkTable shooterLL;
  LimelightPortal limeLL = new LimelightPortal();

  double originalTol = Constants.TURN_TOLERANCE;
  // 
  

  public ShuffleboardTest() {
    // tab.add("***test", 10.0);
    shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter");
    SmartDashboard.putData("print", new PrintTest());



    
    


  }

  @Override
  public void periodic() {
    double current = llMode.getDouble(0);
    shooterLL.getEntry("ledMode").setNumber(current); 

    double tolerance = turnTol.getDouble(0);
    Constants.TURN_TOLERANCE = tolerance;
    

    // System.out.println("***********" + Constants.TURN_TOLERANCE + "**********"); 


    //the mode will change the moment the number changes

    // This method will be called once per scheduler run
  }
}
