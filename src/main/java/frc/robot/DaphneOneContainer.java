/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoPaths.AutoPath1;
// import frc.robot.commands.climber.SemiAutoClimb;
// import frc.robot.commands.climber.SemiAutoPullUp;
// import frc.robot.commands.climber.ToggleClimberGearLock;
import frc.robot.commands.controlpanel.SpinToColor;
import frc.robot.commands.controlpanel.SpinToMidColor;
//import frc.robot.commands.controlpanel.SpinnerCommand;
// import frc.robot.commands.conveyor.ConveyorSpeed;
import frc.robot.commands.intake.IntakeSpeed;
import frc.robot.commands.intake.ToggleIntake;
import frc.robot.commands.swervedrive.HolonomicDriveCommand;
import frc.robot.commands.swervedrive.ZeroNavX;
// import frc.robot.subsystems.Climber.ClimberTalon;
import frc.robot.subsystems.Color.ColorPanelSpinner;
import frc.robot.subsystems.Color.ColorSensor;
import frc.robot.subsystems.ConveyorTalon;
import frc.robot.subsystems.Drive.SwerveDriveModule;
import frc.robot.subsystems.Drive.SwerveDriveSubsystem;
// import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
// import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class DaphneOneContainer {
  private final XboxController mXboxController;
  private final XboxController mXboxController2;

  private final SwerveDriveSubsystem swerveDriveSubsystem;
  private final ColorPanelSpinner colorPanelSpinner;
  private final ColorSensor colorSensor;
  private final Limelight limelight;
  private final ConveyorTalon conveyor;
  // private final Intake intake;
  // private final Shooter shooter;
  private final Compressor compressor;
  // private final ClimberTalon climber;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public DaphneOneContainer() {
    // create all the subsystems needed in this robot
    SwerveDriveModule m0 = new SwerveDriveModule(0, new TalonSRX(DaphneOneConstants.ANGLE1_TALON), new TalonFX(DaphneOneConstants.DRIVE1_TALON), 169); //2020: 70
    SwerveDriveModule m1 = new SwerveDriveModule(1, new TalonSRX(DaphneOneConstants.ANGLE2_TALON), new TalonFX(DaphneOneConstants.DRIVE2_TALON), 176); //2020: 211
    SwerveDriveModule m2 = new SwerveDriveModule(2, new TalonSRX(DaphneOneConstants.ANGLE3_TALON), new TalonFX(DaphneOneConstants.DRIVE3_TALON), 292); //2020: 307
    SwerveDriveModule m3 = new SwerveDriveModule(3, new TalonSRX(DaphneOneConstants.ANGLE4_TALON), new TalonFX(DaphneOneConstants.DRIVE4_TALON), 28); //2020: 150

    swerveDriveSubsystem = new SwerveDriveSubsystem(m0, m1, m2, m3);
    colorPanelSpinner = new ColorPanelSpinner();
    colorSensor = new ColorSensor();
    limelight = new Limelight(swerveDriveSubsystem);
    conveyor = new ConveyorTalon();
    // intake = new Intake();
    // shooter = new Shooter();
    compressor = new Compressor();
    // climber = new ClimberTalon();

    // create the input controllers
    mXboxController = new XboxController(0);
    mXboxController2 = new XboxController(1);

    // setup any default commands
    swerveDriveSubsystem.setDefaultCommand(new HolonomicDriveCommand(swerveDriveSubsystem, mXboxController));
    //colorPanelSpinner.setDefaultCommand(new SpinnerCommand(colorPanelSpinner, mXboxController2));
    //shooter.setDefaultCommand(new SpinShooterMotor());

    // configure the buttons
    configureButtonBindings();
    swerveDriveSubsystem.zeroGyro();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link JoystickButton}.
   */

  private void configureButtonBindings() {
    JoystickButton buttonA = new JoystickButton(mXboxController, XboxController.Button.kA.value);
    JoystickButton buttonX = new JoystickButton(mXboxController, XboxController.Button.kX.value);
    JoystickButton buttonB = new JoystickButton(mXboxController, XboxController.Button.kB.value);
    JoystickButton buttonY = new JoystickButton(mXboxController, XboxController.Button.kY.value);
    JoystickButton buttonLB = new JoystickButton(mXboxController, XboxController.Button.kBumperLeft.value);
    JoystickButton buttonRB = new JoystickButton(mXboxController, XboxController.Button.kBumperRight.value);
    JoystickButton buttonBack = new JoystickButton(mXboxController, XboxController.Button.kBack.value);
    JoystickButton buttonStart = new JoystickButton(mXboxController, XboxController.Button.kStart.value);

    JoystickButton buttonY_2 = new JoystickButton(mXboxController2, XboxController.Button.kY.value);
    JoystickButton buttonX_2 = new JoystickButton(mXboxController2, XboxController.Button.kX.value);
    JoystickButton buttonB_2 = new JoystickButton(mXboxController2, XboxController.Button.kB.value);
    JoystickButton buttonA_2 = new JoystickButton(mXboxController2,XboxController.Button.kA.value);
    JoystickButton buttonLB_2 = new JoystickButton(mXboxController, XboxController.Button.kBumperLeft.value);
    JoystickButton buttonRB_2 = new JoystickButton(mXboxController2, XboxController.Button.kBumperRight.value);
    JoystickButton buttonBack_2 = new JoystickButton(mXboxController2, XboxController.Button.kBack.value);
    JoystickButton buttonStart_2 = new JoystickButton(mXboxController2, XboxController.Button.kStart.value);
    
    // buttonA.whenPressed(new ToggleIntake(intake));
    // buttonB.whileHeld(new IntakeSpeed(intake,-1));
    // buttonX.whileHeld(new IntakeSpeed(intake,.5));
    // buttonY.whileHeld(new ConveyorSpeed(conveyor, -.8));
    // buttonLB.whileHeld(new ConveyorSpeed( conveyor,.5));
    // buttonBack.whenPressed(new ZeroNavX(swerveDriveSubsystem));


    //buttonB_2.whenPressed(new SemiAutoClimb(climber));
   // buttonX_2.whenPressed(new SemiAutoPullUp(climber));
   // buttonY_2.whenPressed(new ToggleClimberGearLock(climber));
    // buttonLB_2.whenPressed(new SpinToColor(colorSensor, colorPanelSpinner));
    // buttonBack_2.whenPressed(new SpinToMidColor(colorSensor, colorPanelSpinner));
    // buttonStart_2.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoPath1(swerveDriveSubsystem);

  }


}