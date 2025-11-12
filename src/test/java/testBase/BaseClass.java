package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
public Logger logger; //log4j
public Properties p;
	
    @Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	public void setup(String os, String br) throws IOException
	{
    	//Loading config.properties file
    	FileReader file=new FileReader("./src//test//resources//config.properties");
    	p=new Properties();
    	p.load(file);
    	
		logger=LogManager.getLogger(this.getClass());
		
		//Remote Environment
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//Remote Browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default:System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		//Local Environment >> Local Browser
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		  switch(br.toLowerCase())
		  {
		    case "chrome":driver=new ChromeDriver();break;
		    case "edge":driver=new EdgeDriver();break;
		    case "firefox":driver=new FirefoxDriver();break;
		    default:System.out.println("Invalid browser name"); return;
		  }
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
    @AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
    
    public String randomeString()
    {
	    String generatedstring = RandomStringUtils.randomAlphanumeric(5);
	    return generatedstring;
	
		}

     public String randomeNumber()
     {
        String generatednumber = RandomStringUtils.randomNumeric(10);
        return generatednumber;

	 }

     public String randomeAlphaNumeic()
     {
         String generatedstring = RandomStringUtils.randomAlphanumeric(3);
         String generatednumber = RandomStringUtils.randomNumeric(3);
         return (generatedstring+"@"+generatednumber);

	}
     
  // Method to capture screenshot and return the file path
     public String captureScreen(String testName) throws IOException {
         // Generate timeStamp
         String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
         
         // Capture screenshot as file
         TakesScreenshot ts = (TakesScreenshot) driver;
         File source = ts.getScreenshotAs(OutputType.FILE);
         
         // Define destination path
         String destination = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
         File finalDestination = new File(destination);
         
         // Copy file to destination
         FileUtils.copyFile(source, finalDestination);
         
         return destination;
     }

}
