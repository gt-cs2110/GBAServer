package to.mar.gbaserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class GBAServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(2346);

            while (true) {
                Socket client = socket.accept();
                InputStreamReader inputStream = new InputStreamReader(client.getInputStream());
                BufferedReader reader = new BufferedReader(inputStream);

                OSUtil.OS operatingSystem = Objects.requireNonNull(OSUtil.getOperatingSystem(), "Your operating system is not supported.");
                String command = operatingSystem.command;

                String input = reader.readLine();

                if (input.startsWith("i")) {
                    command += operatingSystem.gdbSuffix;
                }

                inputStream.close();
                client.close();

                Runtime.getRuntime().exec(command);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
