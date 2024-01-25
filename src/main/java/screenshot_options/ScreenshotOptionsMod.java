package screenshot_options;

import screenshot_options.utils.Translations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenshotOptionsMod {
    public static final String MOD_ID = "screenshot-options";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static void init() {
        System.setProperty("java.awt.headless", "false");
        Translations.loadAllTranslations();
    }
}
