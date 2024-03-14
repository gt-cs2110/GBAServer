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
                input = input.substring(13);

                if (input.endsWith(" GDB")) {
                    command += operatingSystem.gdbSuffix;
                    input = input.substring(0, input.length() - 4);
                }

                inputStream.close();
                client.close();

                Runtime.getRuntime().exec(String.format(command, input));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
