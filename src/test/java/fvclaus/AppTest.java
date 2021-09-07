package fvclaus;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Proxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(false);
            String proxyEnv = System.getenv("HTTPS_PROXY");
            if (proxyEnv != null) {
                Proxy proxy = new Proxy(proxyEnv);
                // proxy.setBypass(System.getenv("NO_PROXY"));
                proxy.setPassword(System.getenv("HTTPS_PROXY_PASSWORD"));
                proxy.setUsername(System.getenv("HTTPS_PROXY_USERNAME"));
                System.out.println("Using proxy server " + proxy.server + ".");
                launchOptions.setProxy(proxy);
            }
            Browser browser = playwright.chromium().launch(launchOptions);
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            Assert.assertTrue(
                "Exception " + page.title() + " to contain Playwright",
                page.title().contains("Playwright")
            );
        }
    }
}
