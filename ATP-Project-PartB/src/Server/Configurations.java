package Server;

        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    private Properties properties;

    private Configurations() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find properties.config");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void saveProperties() {
        try {
            String configFilePath = getClass().getClassLoader().getResource("properties.config").getPath();
            properties.store(new FileOutputStream(configFilePath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty("threadPoolSize", "4"));
    }

    public String getMazeGeneratingAlgorithm() {
        return properties.getProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
    }

    public String getMazeSearchingAlgorithm() {
        return properties.getProperty("mazeSearchingAlgorithm", "BreadthFirstSearch");
    }
}

