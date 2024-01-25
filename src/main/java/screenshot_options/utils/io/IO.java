package screenshot_options.utils.io;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.nio.file.Files;
import java.nio.file.Path;

import static screenshot_options.ScreenshotOptionsMod.LOGGER;

public class IO {
    public static final boolean isHeadless;
    private static final Clipboard clipboard;
    
    static {
        isHeadless = GraphicsEnvironment.isHeadless();
        if (isHeadless) {
            LOGGER.error("The GraphicsEnvironment has been set to headless. Clipboard functionality is unavailable.");
            clipboard = null;
        } else {
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
    }
    
    public static boolean createParentDirectories(Path path) {
        return createDirectories(path.getParent());
    }
    
    public static boolean createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (Exception exception) {
            return false;
        }
        
        return true;
    }
    
    public static void copyToClipboard(Transferable object) {
        clipboard.setContents(object, null);
    }
}
