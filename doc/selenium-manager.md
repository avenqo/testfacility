# Managing Selenium Drivers

The driver and their belonging browser versions are managed by Selenium.

The Selenium Manager may be downloaded from here: https://github.com/SeleniumHQ/selenium/tree/trunk/common/manager

After download, you may disable Gatekeeper before using Selenium Manager.

sudo spctl --master-disable

Example: configure Chrome Browser

./bin/selenium-manager --browser chrome