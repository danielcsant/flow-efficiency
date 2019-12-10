package flowefficiency.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.time.DayOfWeek.*;

public class CycleTimeService {

    final List<LocalDate> holidays = Arrays.asList(
            LocalDate.of(2019, 11, 1),
            LocalDate.of(2019, 12, 6),
            LocalDate.of(2019, 12, 9),
            LocalDate.of(2019, 12, 25)
    );

    public long getLaboralDaysBetween(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(startDate::plusDays)
                // Retain all business days. Use static imports from
                // java.time.DayOfWeek.*
                .filter(t -> Stream.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
                        .anyMatch(t.getDayOfWeek()::equals))

                // Retain only dates not present in our holidays list
                .filter(t -> !holidays.contains(t))

                // Collect them into a List. If you only need to know the number of
                // dates, you can also use .count()
                .count() + 1;
    }
}
