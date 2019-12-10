package flowefficiency.service;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CycleTimeServiceTest {

    @Test
    public void shouldReturnLaboralDaysTest(){
        CycleTimeService cycleTimeService = new CycleTimeService();
        LocalDate startDate = LocalDate.of(2019, 11, 29);
        LocalDate endDate = LocalDate.of(2019, 12, 12);

        long numDays = cycleTimeService.getLaboralDaysBetween(startDate, endDate);

        Assert.assertEquals(8, numDays);
    }

}