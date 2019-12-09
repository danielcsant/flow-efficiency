package flowefficiency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jmgomez on 27/04/17.
 */
public class UserInterface {

    public String getUser(){
        return getPropertyOrEnvVal("user", "JIRA_USER");
    }

    public String getPassword(){
        return getPropertyOrEnvVal("password", "JIRA_PASS");
    }

    private String getPropertyOrEnvVal(String property, String envVal) {
        String value = System.getenv(envVal);

        if (value == null){
            value = get(property);
        }

        return value;
    }

    public String getProject() {
        return get("project");
    }

    private String get(String field) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter "+field+"> ");
        String value = "";
        try {
            value = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
