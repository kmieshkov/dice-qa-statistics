import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

// HOSTING - https://www.infinityfree.net/

// REMOTE MySQL - https://remotemysql.com/

public class DiceQaStatistics {

	public static String userName = "";
	public static String password = "";

	public static void main(String[] args) throws SQLException {

		// get search data from the DB
		String url = "jdbc:mysql://remotemysql.com:3306/l7KuvpmIc9";
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `search_data`");
		int i = 0;
		List<Map<String, String>> presets = new ArrayList<>();
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("job_title", rs.getString("job_title"));
			if (rs.getString("location").equals("Remote Only")) {
				map.put("location", "USA");
			} else {
				map.put("location", rs.getString("location"));
			}
			map.put("period", rs.getString("period"));
			presets.add(i, map);
			i++;
		}

		// main loop
		String result;
		for (Map<String, String> data : presets) {
			result = script(data);
			data.put("result", result);
			workWithDB(con, data);
		}
	}

	public static void workWithDB(Connection con, Map<String, String> data) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `dice` (" +
					"`id` INT(11) NOT NULL PRIMARY KEY auto_increment," +
					"`job_title` VARCHAR(255)," +
					"`location` VARCHAR(50)," +
					"`period` VARCHAR(20)," +
					"`result` VARCHAR(10)," +
					"`date` DATE," +
					"`time` TIME," +
					"`week_day` VARCHAR(10)" +
					");");
			ResultSet rs = stmt.executeQuery("SELECT * FROM `dice` WHERE location = '" + data.get("location") + "' AND period = '" + data.get("period") + "' AND date = '" + java.time.LocalDate.now().toString() + "'");
			if (!rs.next()) {
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO `dice` (job_title, location, period, result, date, time, week_day) VALUES (?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, data.get("job_title"));
				pstmt.setString(2, data.get("location").equals("USA") ? "Remote Only" : data.get("location"));
				pstmt.setString(3, data.get("period"));
				pstmt.setString(4, data.get("result"));
				pstmt.setString(5, java.time.LocalDate.now().toString());
				pstmt.setString(6, java.time.LocalTime.now().toString());
				pstmt.setString(7, LocalDate.parse(java.time.LocalDate.now().toString()).getDayOfWeek().toString());
				pstmt.executeUpdate();
			}
			con.close();
			System.out.println("Success");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String script(Map<String, String> data) {

		// init driver
		System.setProperty("webdriver.chrome.driver", "/Users/kmieshkov/ProgramFiles/chromedriver");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.dice.com/");

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// set Job title
		WebElement searchTerm = driver.findElement(By.xpath("//input[@id='typeaheadInput']"));
		searchTerm.clear();
		searchTerm.sendKeys(data.get("job_title"));

		// set Location
		WebElement locator = driver.findElement(By.id("google-location-search"));
		locator.clear();
		locator.sendKeys(data.get("location"));

		// click Search button
		WebElement searchButton = driver.findElement(By.id("submitSearch-button"));
		searchButton.click();

		// click filter Last 7 Days
		String xpathPeriod;
		if (data.get("period").equals("7")) {
			xpathPeriod = "//button[@data-cy-index='3']";
		} else {
			xpathPeriod = "//button[@data-cy-index='1']";
		}
		WebElement period = driver.findElement(By.xpath(xpathPeriod));
		period.click();

		// if location USA -> set REMOTE ONLY OPTION
		if (data.get("location").equals("USA")) {
			driver.findElement(By.xpath("//button[@aria-label='Filter Search Results by Remote Only']")).click();
		}

		// reload page
		driver.navigate().refresh();

		// Get Total Job Count
		WebElement totalJobCount = driver.findElement(By.id("totalJobCount"));
		String result = totalJobCount.getText().replace(",", "");

		// close WebDriver
		driver.quit();
		return result;
	}
}
