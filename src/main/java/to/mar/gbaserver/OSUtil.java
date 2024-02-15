package to.mar.gbaserver;

public class OSUtil {

    public enum OS {
        WINDOWS("mGBA.exe App.gba", " --gdb"), MAC("open -n App.gba -a mGBA.app", " --args --gdb"), LINUX("./mGBA App.gba", " --gdb");

        final String command;
        final String gdbSuffix;
        OS(String command, String gdbSuffix) {
            this.command = command;
            this.gdbSuffix = gdbSuffix;
        }
    }

    public static OS getOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return OS.WINDOWS;
        }

        if (osName.contains("mac")) {
            return OS.MAC;
        }

        if (osName.contains("nix") || osName.contains("nux")) {
            return OS.LINUX;
        }

        return null;
    }

}
