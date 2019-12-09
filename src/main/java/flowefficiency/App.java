package flowefficiency;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;

public class App {

    public static void main(String[] args) {
        new Service().start();
    }
}
