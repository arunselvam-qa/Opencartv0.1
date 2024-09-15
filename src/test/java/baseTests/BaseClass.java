package baseTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","Browser"})
	public void setUp(String os, String br) throws IOException, URISyntaxException
	{

		logger = LogManager.getLogger(this.getClass());

		//Loading properties file
		FileInputStream file = new FileInputStream(".//src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);

		if(prop.getProperty("executionEnv").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linus"))
			{
				cap.setPlatform(Platform.LINUX);
			}

			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			
			default: logger.fatal("Invalid Broswer"); return;
			}
			
			//driver=new RemoteWebDriver(new URI("http://192.168.1.35:4444/wd/hub").toURL(),cap);
			driver=new RemoteWebDriver(new URI("http://localhost:4444/").toURL(),cap);

		}

		if(prop.getProperty("executionEnv").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase()) 
			{
			case "edge": driver = new EdgeDriver(); break;
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;

			default: logger.fatal("Invalid Broswer"); return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("appURL1"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}


	public String randomAlphabeticString(int length)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a','z').get();
		String randomString = generator.generate(length);
		return randomString;
	}
	public String randomNumericString(int length)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','9').get();
		String randomString = generator.generate(length);
		return randomString;
	}
	public String randomAphNumString(int length)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','z').get();
		String randomString = generator.generate(length);
		return randomString;
	}

	public String captureScreenshot(String name)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String targetPath = System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File target = new File(targetPath);
		source.renameTo(target);

		return targetPath;
	}

}
