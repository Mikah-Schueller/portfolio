# Robot Code

### Description
The **Robot Code** repository contains the code I developed for my high school's FRC competition, *Charged Up*. The code was primarily adapted from previous years' work, with added functionality to improve the robot's performance during the competition. The robot was tasked with moving an arm to pick up cones and cubes, with additional systems for intake, autonomous functionality, distance detection, and auto-balancing using a PID loop.

---

## Features
   - **Arm Movement**: Code for controlling the robot's arm to pick up cones and cubes.
   - **Intake System**: Mechanism for grabbing objects.
   - **Autonomous Code**: Functions for running the robot autonomously, without human control.
   - **Limelight Distance Detection**: Uses a Limelight camera to detect distances for precise robot positioning.
   - **Auto-Balancing with PID**: Uses a PID loop for auto-balancing the robot to prevent tipping.
   - **Gear Shifting**: Controls the robot's gear shift for different driving conditions.
   - **Sustainability Guide**: A guide I wrote to help new coders in the club get started with coding in the FRC environment.

---

## Prerequisites
1. **CTRE Talon FX** (or equivalent for motor control)
2. **FRC Driver Station**
3. **Limelight Camera** for distance detection (optional)

---

## Installation
  1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Robot-Code.git
   ```
  2. Navigate to the project directory:
  ```bash
  cd Robot-Code
  ```
  3. Ensure all dependencies are installed and set up for FRC, including motor controllers, sensors, and the Limelight camera.

---

## Usage Instructions
1. **Robot Setup**:
  - Ensure that all robot hardware is connected and functional.
  - Set up the Limelight camera if using for distance detection.
2. **Running Autonomous**:
  - The robot will automatically execute the autonomous code when enabled during a match.
3. **Teleoperated Control**:
  - Use the FRC Driver Station to control the robot during teleop mode.
4. **PID Tuning**:
  - Adjust the PID constants in the code if needed for better auto-balancing performance.

---


## Customization
  - **Arm Controls**: Modify the motor commands and sensors for your specific robot configuration.
  - **Intake System**: Adjust the intake motor speed and positioning as needed.
  - **Limelight Settings**: Update the Limelight settings for your specific robot setup.
  - **Auto-Balancing**: Modify PID values in the code to optimize for your robot’s center of gravity and wheel configuration.

## Disclaimer
This code is provided as-is, for educational and development purposes within the FRC community. Always test on your robot before competition.
