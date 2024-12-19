# Automated Tee Time Booker

### Description
The **Automated Tee Time Booker** is a Python script designed to book tee times at your local golf course faster than any human could. Tee time slots fill up within seconds, making manual booking impractical. This program automates the process using Selenium, ensuring you never miss your preferred slot again.

---

## Features
- **Automated Tee Time Booking**: Automatically books a tee time at the specified time, minimizing delays caused by human input.
- **NTP Time Accuracy**: Utilizes `ntplib` to synchronize with global time servers for precise timing.
- **Customizable Start Time**: Allows the user to specify the earliest and latest acceptable tee times.
- **Preconfigured Booking Details**: Books for 4 players, 18 holes, and no golf cart (modifiable by adjusting XPaths in the code).
- **Reusable**: Can be converted into an `.exe` for simplified use on any machine.

---

## Prerequisites
To use the script in its current form:
1. **Python 3.x Installed**: [Download Python](https://www.python.org/downloads/)
2. **Dependencies Installed**:
   ```bash
   pip install selenium ntplib
3. **Google Chrome Installed**: Download Chrome
4. **ChromeDriver Installed**: Ensure the ChromeDriver version matches your Chrome browser version. Download ChromeDriver
Environment Variables:
- golf_email: Your login email for the golf course booking website.
- golf_password: Your password for the booking website.
Alternatively, if packaged as an .exe:
- The environment variables can be hardcoded into the script during compilation.

---

## Installation
1. Clone the repository:
```bash
git clone https://github.com/your-username/automated-tee-time-booker.git
2. Navigate to the project directory:
```bash
cd automated-tee-time-booker
3. Install required libraries:
```bash
pip install selenium ntplib

---

## Usage Instructions
1. **Setup Environment Variables**:
- Add golf_email and golf_password to your system's environment variables or modify the script to include them as hardcoded values.
2. **Run the Script**:
- Open the script in your Python editor (e.g., VS Code, PyCharm).
- Run the script close to the desired booking time.
- The script will:
a. Log in to the golf course booking website.
b. Wait until tee times become available.
c. Automatically book a time based on your preferences.
3. **Post-Booking**:
- The script will display the booked tee time and keep the browser open for verification.

---

## Customization
While the script is tailored for specific booking preferences, it can be modified for:
- **Different Number of Players**: Update the XPath for selecting the number of players.
- **Hole Preferences**: Change the XPath to select 9 or 18 holes.
- **Golf Cart Option**: Modify the XPath to include/exclude a golf cart.
- **Booking Times**: Adjust the earliest and latest acceptable times by editing the start_hour, start_min, latest_hour, and latest_min functions.
**Note**: To customize, users need to locate the appropriate XPaths for their golf course's booking website.

---

## Disclaimer
This script is provided for educational purposes only. Automated interactions with websites may violate terms of service, so use responsibly and at your own discretion.
