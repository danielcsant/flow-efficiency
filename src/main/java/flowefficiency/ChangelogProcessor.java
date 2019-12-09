package flowefficiency;

import net.rcarz.jiraclient.ChangeLogEntry;
import net.rcarz.jiraclient.ChangeLogItem;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.User;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ChangelogProcessor {

    public UserStory getUserStoryInfo(Issue issue) {
        UserStory userStory = null;
        try{
            Date doingDate = null;
            Date doneDate = null;
            long cycleTime = -1;
            int segundos = -1;
            for (ChangeLogEntry entry : issue.getChangeLog().getEntries()) {
                for (ChangeLogItem item : entry.getItems()) {
                    if (item.getField().equals("status")) {
                        if (item.getFromString().equals("To-Do") && item.getToString().equals("Doing")){
                            doingDate = entry.getCreated();
                        } else if (item.getFromString().equals("UAT")
                                && item.getToString().equals("Planificada en Agenda")){
                            doneDate = entry.getCreated();
                        }

                        if (doingDate != null && doneDate != null){
                            long diff = doneDate.getTime() - doingDate.getTime();
                            cycleTime = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        }
                    }

                    if (item.getField().equals("timespent")) {
                        segundos = Integer.parseInt(item.getToString());
                    }
                }
            }

            if (segundos != -1 && cycleTime != 0) {
                float timeSpent = (float) segundos/3600;
                float flowEfficiency =  100 * timeSpent / (cycleTime * 8);

                System.out.println("Tiempo dedicado: " + timeSpent + " horas");
                System.out.println("Tiempo de ciclo: " + cycleTime + " días");
                System.out.println("Eficiencia de flujo: " + flowEfficiency + " %");

                userStory = new UserStory(
                        issue.getKey(),
                        issue.getSummary(),
                        timeSpent,
                        cycleTime,
                        flowEfficiency
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return userStory;
    }

}
