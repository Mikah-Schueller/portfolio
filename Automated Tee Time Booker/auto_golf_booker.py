from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from datetime import datetime, timedelta
from selenium import webdriver
import time
import ntplib
import os

buffer = 0

#Function checks if button exists
def check_exists_by_xpath(xpath):
   try:
      web.find_element(By.XPATH, xpath)
   except NoSuchElementException:
      return False
   return True

#Function to get the global time in EST
def get_ntp_time(server = 'time.google.com'):
   client = ntplib.NTPClient()
   response = client.request(server)
   ntp_time = datetime.utcfromtimestamp(response.tx_time)
   adjusted_time = ntp_time - timedelta(hours = 4)
   return adjusted_time

#Functions determine the earliest time that we want to book based on the current month (latest time is always 10:00am)
def start_hour(mon):
   hour = [7, 6, 6, 6, 5, 5, 5, 6, 6, 6, 7, 7]
   return hour[mon-1]
    
def start_min(mon):
   min = [11, 53, 35, 16, 58, 40, 58, 16, 35, 53, 0, 30]
   return min[mon-1]

#Gets the day that we want to book (two weeks in the furture) and separates it into day, month, and year, storing each in vriables
today_day = get_ntp_time() + timedelta(weeks = 2)
month = int(today_day.strftime("%m"))
day = int(today_day.strftime("%d"))
year = int(today_day.strftime("%g"))

#Stores the earliest and latest start times based on the above functions
earliest_hour = start_hour(month)
earliest_min = start_min(month)
latest_hour = 10
latest_min = 00

#This function checks to see if the selected time is in the range of the earliest and latest start times previously defined
def validTime(hr, min):
   hr = int(hr)
   min = int(min)
   if hr < latest_hour and hr > earliest_hour:
      return True
   elif earliest_hour == latest_hour and latest_hour == hr and min >= earliest_min and min <= latest_min:
      return True
   elif ((hr == latest_hour and min <= latest_min) or (hr == earliest_hour and min >= earliest_min)) and latest_hour != earliest_hour:
      return True
   else:
      return False

#Starts a new chome window and goes to the golf course website
web = webdriver.Chrome()
web.get('https://foreupsoftware.com/index.php/booking/21593/8314#teetimes')

# time.sleep(0.1)

#Clicks the button to book a time as a resident
while (not check_exists_by_xpath('//*[@id="content"]/div/button[1]')):
   pass
resident = web.find_element(By.XPATH, '//*[@id="content"]/div/button[1]')
resident.click()

# time.sleep(0.1)

#Clicks a drop down menu to find the login button
while (not check_exists_by_xpath('//*[@id="navigation"]/div/div[1]/button')):
   pass
drop_down = web.find_element(By.XPATH, '//*[@id="navigation"]/div/div[1]/button')
drop_down.click()

time.sleep(0.15)

#Clicks the login button
while (not check_exists_by_xpath('//*[@id="bs-example-navbar-collapse-1"]/ul[1]/li[4]/a')):
   pass
log_in = web.find_element(By.XPATH, '//*[@id="bs-example-navbar-collapse-1"]/ul[1]/li[4]/a')
log_in.click()

time.sleep(0.1)

#Enters the email and password into the login field
user_email = os.environ['golf_email']
email = web.find_element(By.XPATH, '//*[@id="login_email"]')
email.send_keys(user_email)
user_password = os.environ['golf_password']
password = web.find_element(By.XPATH, '//*[@id="login_password"]')
password.send_keys(user_password)

#Submits the login form(button is also called login but variable is submit for simplicity)
submit = web.find_element(By.XPATH, '//*[@id="login"]/div/div[3]/div[1]/button[1]')
submit.click()

#Selects that we want a game with 4 players
while (not check_exists_by_xpath('//*[@id="nav"]/div/div[3]/div/div/a[4]')):
   pass
players_4 = web.find_element(By.XPATH, '//*[@id="nav"]/div/div[3]/div/div/a[4]')
players_4.click()

#Chooses the day we want to book on the calendar
date_field = web.find_element(By.XPATH, '//*[@id="date-field"]')
date_field.send_keys(Keys.BACK_SPACE*10)
booking_date = f'{month}-{day}-20{year}'
date_field.send_keys(booking_date)
time.sleep(0.1)
date_field.send_keys(Keys.ENTER)

#Waits until 7pm (when the tee times become available)
hour = int((get_ntp_time()).strftime("%H"))
while hour < 19:
   hour = int((get_ntp_time()).strftime("%H"))

#Selects that we want to play a game of 18 holes
while (not check_exists_by_xpath('//*[@id="nav"]/div/div[4]/div[2]/div/a[2]')):
   pass
holes_18 = web.find_element(By.XPATH, '//*[@id="nav"]/div/div[4]/div[2]/div/a[2]')
holes_18.click()

#Checks to see if a booking has already been made
while (not check_exists_by_xpath('//*[@id="content"]/div/h3')):
   #Books the first time slot that meets all the above criteria, if none exist within the desired time range, the earliest slot is selected
   while (not check_exists_by_xpath('//*[@id="times"]/div/div[2]')):
      pass
   test = web.find_element(By.XPATH, '//*[@id="times"]/div/div[1]')
   test_time = test.text[0:6:1]

   x = 1
   while 'a' in test_time:
      if validTime(test_time[0:2:1].strip(':'), test_time[2:5:1].strip(':').strip('a').strip('p')):
         break
      x+=1
      test = web.find_element(By.XPATH, f'//*[@id="times"]/div/div[{x+1}]')
      test_time = test.text[0:6:1]

   if 'p' in test_time:
      test = web.find_element(By.XPATH, '//*[@id="times"]/div/div[1]')

   result = test.text[0:7:1]
   test.click()

   #Selects that we do not want a golf cart included in the booking
   while (not check_exists_by_xpath('//*[@id="book_time"]/div/div[2]/div[5]/div[2]/div/a[1]')):
      pass
   #------------------------------------------------------------------------------------------------------------------------------------
   time.sleep(0.1)
   #------------------------------------------------------------------------------------------------------------------------------------
   cart = web.find_element(By.XPATH, '//*[@id="book_time"]/div/div[2]/div[5]/div[2]/div/a[1]')
   cart.click()

   #Selects the option to book the selected time

   book_time = web.find_element(By.XPATH, '//*[@id="book_time"]/div/div[3]/button[1]')
   book_time.click()

   while (not check_exists_by_xpath('//*[@id="payment_selection"]/div/div[3]/button[1]') and not check_exists_by_xpath('//*[@id="select-credit-card"]/div/div[3]/div/button')):
      pass

   if check_exists_by_xpath('//*[@id="payment_selection"]/div/div[3]/button[1]'):
      later = web.find_element(By.XPATH, '//*[@id="payment_methods"]/li[1]/label/div[1]/input')
      later.click()
      payment_type = web.find_element(By.XPATH, '//*[@id="payment_selection"]/div/div[3]/button[1]')
      payment_type.click()

   #Confirm that we want to book this slot
   while (not check_exists_by_xpath('//*[@id="select-credit-card"]/div/div[3]/div/button')):
      pass
   confrim = web.find_element(By.XPATH, '//*[@id="select-credit-card"]/div/div[3]/div/button')
   confrim.click()

   while (check_exists_by_xpath('//*[@id="select-credit-card"]/div/div[3]/div/button')):
      pass

#Prints the booking time
print(f"\nA booking has been made for {result}")

#Keeps the window open
while True:
   pass