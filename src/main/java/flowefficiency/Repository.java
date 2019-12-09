package flowefficiency;

import net.rcarz.jiraclient.User;

import java.io.*;
import java.util.List;

public class Repository {
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    private static final String CSV_SEPARATOR = ";";

    public void toCsv(List<UserStory> userStories) {
        {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("issues.csv"), "UTF-8"));
                for (UserStory userStory : userStories)
                {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(userStory.getKey());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(userStory.getSummary());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(userStory.getEpicName());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(userStory.getCycleTime());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(userStory.getTimeSpent());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(userStory.getFlowEfficiency());
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            }
            catch (UnsupportedEncodingException e) {}
            catch (FileNotFoundException e){}
            catch (IOException e){}
        }
    }
}
