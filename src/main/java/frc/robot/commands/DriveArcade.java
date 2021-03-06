/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

public class DriveArcade extends Command {
  private DriveTrain m_DriveTrain;
  private Joystick Input;

  private double rot,speed;
  public DriveArcade() {
    this.m_DriveTrain = DriveTrain.getInstance();
    requires(this.m_DriveTrain);
    Input = OI.driverJoystick;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.rot = this.Input.getRawAxis(4)*SmartDashboard.getNumber("Max speed",0.7);
    this.speed = this.Input.getRawAxis(1)*SmartDashboard.getNumber("Max speed",1);
    SmartDashboard.putNumber("speed", this.speed);

    if(speed < 0.2 && speed > -0.2){
      speed = 0;
    }
    if(rot < 0.2 && rot > -0.2){
      rot = 0;
    }
    System.out.println(this.speed);
    if(speed>0)
    // m_DriveTrain.diffDrive.curvatureDrive(-speed, rot*0.2, false);
    m_DriveTrain.diffDrive.arcadeDrive(-speed, rot);
    
    else
    m_DriveTrain.diffDrive.curvatureDrive(-speed, rot, true);
    this.m_DriveTrain.UpdateOdometry();
  }

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
