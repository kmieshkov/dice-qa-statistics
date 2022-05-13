import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

// HOSTING - https://www.infinityfree.net/

// REMOTE MySQL - https://remotemysql.com/

public class DiceQaStatistics {

	private static final String jdbc = "jdbc:mysql://";
	private static String userName;
	private static String password;
	private static String db_url;
	private static String url;
	private static final By by_searchTerm = By.xpath("//input[@id='typeaheadInput']");
	private static final By by_locator = By.id("google-location-search");
	private static final By by_searchButton = By.id("submitSearch-button");
	private static final String period7 = "//button[@data-cy-index='3']";
	private static final String period1 = "//button[@data-cy-index='1']";
	private static final By remoteOnly = By.xpath("//button[@aria-label='Filter Search Results by Remote Only']");
	private static final By by_totalJobCount = By.id("totalJobCount");
	public static Properties properties = new Properties();

	private static void init() throws IOException {

		properties.load(new FileInputStream(System.getenv("HOME") + "/Projects/IdeaProjects/dice-qa-statistics/data.properties"));
		userName = properties.getProperty("userName");
		password = properties.getProperty("password");
		db_url = properties.getProperty("db_url");
		url = properties.getProperty("url");
	}

	public static void main(String[] args) throws SQLException, IOException {
		init();
		// get search data from the DB
		Connection con = DriverManager.getConnection(jdbc + db_url, userName, password);
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

		// init driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--window-size=1920,1200");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(url);
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// main loop
		String result;
		for (Map<String, String> data : presets) {
			result = script(driver, data);
			data.put("result", result);
			workWithDB(data);
		}
		// close WebDriver
		driver.quit();
	}

	public static void workWithDB(Map<String, String> data) throws SQLException {
		Connection con = DriverManager.getConnection(jdbc + db_url, userName, password);
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM `dice` WHERE location = '" + data.get("location") + "' AND period = '" + data.get("period") + "' AND date = '" + LocalDate.now() + "'");
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
		System.out.println("Success: " + (data.get("location").equals("USA") ? "Remote Only" : data.get("location")) + " - " + data.get("period"));
	}

	public static String script(WebDriver driver, Map<String, String> data) {

		// set Job title
		WebElement searchTerm = driver.findElement(by_searchTerm);
		searchTerm.clear();
		searchTerm.sendKeys(data.get("job_title"));

		// set Location
		WebElement locator = driver.findElement(by_locator);
		locator.clear();
		locator.sendKeys(data.get("location"));

		// click Search button
		WebElement searchButton = driver.findElement(by_searchButton);
		searchButton.click();

		// click filter Last 7 Days
		String xpathPeriod;
		if (data.get("period").equals("7")) {
			xpathPeriod = period7;
		} else {
			xpathPeriod = period1;
		}
		WebElement period = driver.findElement(By.xpath(xpathPeriod));
		period.click();

		// if location USA -> set REMOTE ONLY OPTION
		if (data.get("location").equals("USA")) {
			driver.findElement(remoteOnly).click();
		}

		// reload page
		driver.navigate().refresh();

		// Get Total Job Count
		WebElement totalJobCount = driver.findElement(by_totalJobCount);
		String result = totalJobCount.getText().replace(",", "");

		driver.navigate().to(url);

		return result;
	}
}
