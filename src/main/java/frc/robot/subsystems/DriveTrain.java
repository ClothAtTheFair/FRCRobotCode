/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;


import frc.robot.Robot;
import jdk.jfr.MemoryAddress;


public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands. 
  final SpeedController _leftTalon = new Talon(0); 
  Talon rightTalon = new Talon(0);
  Talon lefTalon = new Talon(1);
  private final Encoder _rightEncoder = new Encoder(1, 2, true, EncodingType.k4X);
  private final Encoder _leftEncoder = new Encoder(3, 4, true, EncodingType.k4X);

public DifferentialDrive driveTrain(){
  DifferentialDrive myDrive = new DifferentialDrive(lefTalon, rightTalon);

  _rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
  _leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
  //this if else converts the encoders to feet
  if (Robot.isReal()){
    _rightEncoder.setDistancePerPulse(0.0785398);
    _leftEncoder.setDistancePerPulse(0.0785398);
  }
  else {
    _rightEncoder.setDistancePerPulse((4.0*Math.PI)/(360.0*12));
    _leftEncoder.setDistancePerPulse((4.0*Math.PI)/(360.0*12));
  }

  return myDrive;

}



  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //this shouldn't work but I want to push as it is midnight
  public void arcadeDrive(Joystick joy, DifferentialDrive myDrive){
    myDrive.arcadeDrive(joy.getY(),joy.getX());
  }

  public Encoder getRightEncoder(){
    return _rightEncoder;
  }
  public Encoder getLeftEncoder(){
    return _leftEncoder;
  }
}
