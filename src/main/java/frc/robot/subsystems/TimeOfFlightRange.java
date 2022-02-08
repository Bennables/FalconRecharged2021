/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TimeOfFlightRange extends SubsystemBase {
  /**
   * Creates a new Range.
   */
  private TimeOfFlight sensor;

  public TimeOfFlightRange() {
    sensor = new TimeOfFlight(2);
    sensor.setRangingMode(TimeOfFlight.RangingMode.Long, 24);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //ask hajel for usage of this
  }

  public double getDistanceSensor() {
    double distance = sensor.getRange(); //gets distance from target from timeOfFlight sensor in mm
    return distance / 25.4;
  }
}
