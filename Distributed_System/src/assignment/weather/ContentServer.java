package assignment.weather;

import java.io.*;
import java.util.*;

public class ContentServer extends WeatherConnection {
    /**
     *
     * @param serverAddress : the address of the aggregation server
     * @param weatherFile: the weather.txt
     *  Start content server and sending http put every 20 seconds
     */
    public ContentServer(String serverAddress,File weatherFile) {
        super(serverAddress);
        try {
            while (!socket.isClosed()) {
                System.out.println("Send data from content server");
                LinkedHashMap<String,String> weatherData = readingWeatherData(weatherFile);
                String httpPostMessage = "PUT /weather.json HTTP/1.1\r\n";
                httpPostMessage += "User-Agent: ATOMClient/1/0\r\n";
                httpPostMessage += "Content-Type: application/json\r\n";
                lamportClock.increaseCounter();
                String jsonString = JsonParser.getInstance().writeJson(lamportClock,weatherData);
                httpPostMessage += "Content-Length: " + jsonString.length()  + "\r\n";
                httpPostMessage += (jsonString + "\r\n");
                socketWriter.println(httpPostMessage);
                socketWriter.flush();

                String inMsg = socketReader.readLine();
                while (!inMsg.contains("WeatherInformation") && !inMsg.contains("lamportClock"))
                {
                    System.out.println(inMsg);
                    inMsg = socketReader.readLine();
                }
                System.out.println(inMsg);
                WeatherInformation weatherInformation = JsonParser.getInstance().readJson(inMsg);
                lamportClock.updateCounterReceive(weatherInformation.clockCounter);
                Thread.sleep(20000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(socket != null)
            {
                try{
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        if(args.length >= 2)
        {
            File weatherFile = new File(args[1]);
            try {
                ContentServer contentServer = new ContentServer(args[0],weatherFile);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Please input the server address and the path to the weather file");
            System.exit(0);
        }
    }
    private LinkedHashMap<String,String> readingWeatherData(File weatherFile)
    {
        LinkedHashMap<String,String> weatherData = new LinkedHashMap<>();
        try {
            Scanner readingScanner = new Scanner(weatherFile);
            while (readingScanner.hasNextLine()) {
                String[] tokens = readingScanner.nextLine().split(":");
                weatherData.put(tokens[0],tokens[1]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return weatherData;
    }
}
