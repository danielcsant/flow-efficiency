package flowefficiency.service;

import flowefficiency.App;
import flowefficiency.model.UserStory;
import net.rcarz.jiraclient.*;

import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    private JiraClient jira;

    public ReportGenerator() {
        BasicCredentials creds = new BasicCredentials(AppProperties.getJiraUser(), AppProperties.getJiraPass());
        jira = new JiraClient(AppProperties.getBaseUri(), creds);
    }


    public void start() {
        try {
            List userStories = new ArrayList();
            for (Issue issueIter : getIssues()) {
                /* Retrieve issue GEMITPM-1683 from JIRA. We'll get an exception if this fails. */
                Issue issue = jira.getIssue(issueIter.getKey(), "summary,customfield_10810", "changelog");
                UserStory userStory = new ChangelogProcessor().getUserStoryInfo(issue);
                if (userStory != null) {
                    Issue epic = jira.getIssue(issue.getField("customfield_10810").toString(), "summary", "changelog");
                    userStory.setEpicName(epic.getSummary());
                    userStories.add(userStory);
                }
            }

            new CsvGenerator().toCsv(userStories);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private List<Issue> getIssues() throws JiraException {
        /*Issue.SearchResult result = jira.searchIssues(
                "project = GEMITPM AND " +
                        "issueType=Story AND " +
                        "status in (\"Planificada en Agenda\", \"Done\") AND " +
                        "issuekey in (childIssuesOf(GEMITPM-705), childIssuesOf(GEMITPM-1363), childIssuesOf(GEMITPM-1364), childIssuesOf(GEMITPM-1365),childIssuesOf(\"GEMITPM-1714\"), GEMITPM-705, GEMITPM-1363, GEMITPM-1364, GEMITPM-1365,GEMITPM-1714)",
                "summary",
                1000);*/
        Issue.SearchResult result = jira.searchIssues(
                AppProperties.getJql(),
                "summary",
                1000);

        return result.issues;
    }

}
