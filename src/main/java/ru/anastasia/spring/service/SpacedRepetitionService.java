package ru.anastasia.spring.service;


import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Practice;

import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class SpacedRepetitionService {

    final
    PracticeService practiceService;

    public SpacedRepetitionService(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

    public String showTimeLeft (Folder folder){ // показать оставшееся время
        if(practiceService.checkIfExists(folder)) {
            Practice practice = practiceService.getPractice(folder);
            Duration duration = getTimeLeft(practice);
            if (duration.isNegative()){
                return "0 дней 0 ч. 0 мин. 0 с.";
            }
            return duration.toDaysPart() + " дн. " + duration.toHoursPart() +
                    " ч. " + duration.toMinutesPart() + " мин. " +
                    duration.toSecondsPart() + " с.";
        }
        return "нет данных";
    }

    public Duration getTimeLeft (Practice practice) { // получить время до следующего повторения
        Calendar calendarFirst = new GregorianCalendar();
        calendarFirst.setTime(practice.getFirstPracticeDate().getTime());
        switch (practice.getRepetitionStage()){
            case 1:
                calendarFirst.add(Calendar.MINUTE, 2);
                break;
            case 2:
                calendarFirst.add(Calendar.MINUTE, 10);
                break;
            case 3:
                calendarFirst.add(Calendar.HOUR,1);
                break;
            case 4:
                calendarFirst.add(Calendar.HOUR, 5);
                break;
            case 5:
                calendarFirst.add(Calendar.DAY_OF_MONTH,1);
                break;
            case 6:
                calendarFirst.add(Calendar.DAY_OF_MONTH,5);
                break;
            case 7:
                calendarFirst.add(Calendar.DAY_OF_MONTH,25);
                break;
            case 8:
                calendarFirst.add(Calendar.MONTH,4);
                break;
            case 9:
                calendarFirst.add(Calendar.YEAR,2);
                break;
        }
        return Duration.between(Calendar.getInstance().toInstant(),calendarFirst.toInstant());
    }
}
