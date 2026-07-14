import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;

public class MobileTest {
    public static void main(String[] args) throws Exception {
        // 1. Setup connection to mobile app
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Emulator");
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723"), options);

        try {
            // 2. Log in automatically
            Thread.sleep(3000);
            driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
            driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
            driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
            Thread.sleep(3000);

            // 3. Add item to cart
            driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])")).click();
            Thread.sleep(3000);

            // 4. Go to checkout and fill details
            driver.findElement(AppiumBy.accessibilityId("test-Cart")).click();
            Thread.sleep(3000);
            driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
            Thread.sleep(3000);
            
            driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys("John");
            driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys("Doe");
            driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys("12345");
            Thread.sleep(3000);
            driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
            Thread.sleep(3000);

            // 5. Finish checkout and verify success
            driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
            Thread.sleep(3000);
            
            String successText = driver.findElement(AppiumBy.xpath("//*[@text='THANK YOU FOR YOUR ORDER']")).getText();
            System.out.println("Success: " + successText);
            Thread.sleep(3000);

        } finally {
            driver.quit();
        }
    }
}













