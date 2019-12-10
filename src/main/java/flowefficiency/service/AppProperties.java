package flowefficiency.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private Properties prop;
    private static AppProperties appProperties;

    public AppProperties() {
        try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return getProperties().getProperty("jira.baseuri");
    }

    public static String getJiraUser() {
        return getProperties().getProperty("jira.user");
    }

    public static String getJiraPass() {
        return getProperties().getProperty("jira.pass");
    }

    public static String getJql() {
        return getProperties().getProperty("jira.jql");
    }

    public static Properties getProperties() {
        if (appProperties == null) {
            appProperties = new AppProperties();
        }
        return appProperties.prop;
    }


}
