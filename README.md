# About project

This project allows to get statistics from the Dice website on the QA positions in the USA and store data into the remote MySQL

* Language: Java
* DB: MySQL
* Tools: Selenium with Chromedriver

# Example

<img src="/example/example.gif">
The project is running in the backgroun, if you want to see actual web-browser window you have to **comment** options (line 91 of the code)

# How to use?
* Clone repo to your local machine
* Update *chromedriver* according to your Chrome browser on the local machine
* Add external libraries to the project structure (*IntelliJ IDEA: File > Project Structure > Modules*)
 * *chromedriver* and *external_libraries* located in the root directory of the project

If you using Windows - add extension to the "cromedriver" in the script method (89 line of code)
