package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
        List<TrainingSession> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        List<TrainingSession> tuesday = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);

        //Проверить, что за понедельник вернулось одно занятие
        Assertions.assertEquals(1, monday.size());

        //Проверить, что за вторник не вернулось занятий
        Assertions.assertEquals(0,tuesday.size());

    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<TrainingSession> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        List<TrainingSession> thursday = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        List<TrainingSession> friday = timetable.getTrainingSessionsForDay(DayOfWeek.FRIDAY);

        // Проверить, что за понедельник вернулось одно занятие
        Assertions.assertEquals(1, monday.size());

        // Проверить, что за вторник вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        Assertions.assertEquals(2, thursday.size());

        Assertions.assertEquals(new TimeOfDay(13,0), thursday.get(0).getTimeOfDay());
        Assertions.assertEquals(new TimeOfDay(20,0), thursday.get(1).getTimeOfDay());

        // Проверить, что за пятницу не вернулось занятий
        Assertions.assertEquals(0, friday.size());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessionsInOneTime() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);

        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);

        List<TrainingSession> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        List<TrainingSession> thursday = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);

        Assertions.assertEquals(1, monday.size());
        Assertions.assertEquals(2, thursday.size());

    }


    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        List<TrainingSession> trainIn1300 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                new TimeOfDay(13,0));
        List<TrainingSession> trainIn1400 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                new TimeOfDay(14,0));

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        Assertions.assertEquals(1, trainIn1300.size());

        //Проверить, что за понедельник в 14:00 не вернулось занятий
        Assertions.assertEquals(0, trainIn1400.size());
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeInOneTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        TrainingSession firstTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession secondTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(firstTrainingSession);
        timetable.addNewTrainingSession(secondTrainingSession);

        List<TrainingSession> trainIn1300 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                new TimeOfDay(13, 0));

        Assertions.assertEquals(2, trainIn1300.size());
    }


    @Test
    void getCountByCoachesForOneCoach() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession mondayChildTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession mondayChildTrainingSession1 = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(15, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession1);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);


        List<CounterOfTrainings> listOfCoaches = timetable.getCountByCoaches();
        System.out.println(listOfCoaches);
        Assertions.assertEquals(1, listOfCoaches.size());
        Assertions.assertEquals(coach, listOfCoaches.get(0).getCoach());
        Assertions.assertEquals(4,listOfCoaches.get(0).getCoachCountOfSessions());
    }

    @Test
    void getCountByCoachesForThreeCoach() {
        Timetable timetable = new Timetable();

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach1 = new Coach("Иванов", "Сергей", "Петрович");
        Coach coach2 = new Coach("Петров", "Павел", "Александрович");

        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(15, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        TrainingSession wednesdayAdultTrainingSession = new TrainingSession(groupAdult, coach2,
                DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);

        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(15, 0));
        TrainingSession sundayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.SUNDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(sundayChildTrainingSession);


        List<CounterOfTrainings> listOfCoaches = timetable.getCountByCoaches();

        Assertions.assertEquals(3, listOfCoaches.size());

        Assertions.assertEquals(coach, listOfCoaches.get(0).getCoach());
        Assertions.assertEquals(3,listOfCoaches.get(0).getCoachCountOfSessions());

        Assertions.assertEquals(coach1, listOfCoaches.get(1).getCoach());
        Assertions.assertEquals(2,listOfCoaches.get(1).getCoachCountOfSessions());

        Assertions.assertEquals(coach2, listOfCoaches.get(2).getCoach());
        Assertions.assertEquals(1,listOfCoaches.get(2).getCoachCountOfSessions());
    }

    @Test
    void getCountByCoachesForFourCoach() {
        Timetable timetable = new Timetable();

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach1 = new Coach("Иванов", "Сергей", "Петрович");
        Coach coach2 = new Coach("Петров", "Павел", "Александрович");
        Coach coach3 = new Coach("Сидоров", "Иван", "Александрович");

        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(15, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        TrainingSession wednesdayAdultTrainingSession = new TrainingSession(groupAdult, coach2,
                DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);

        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(15, 0));
        TrainingSession sundayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.SUNDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(sundayChildTrainingSession);

        TrainingSession fridayAdultTrainingSession = new TrainingSession(groupAdult, coach3,
                DayOfWeek.FRIDAY, new TimeOfDay(15, 0));
        TrainingSession fridayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.FRIDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(fridayAdultTrainingSession);
        timetable.addNewTrainingSession(fridayChildTrainingSession);


        List<CounterOfTrainings> listOfCoaches = timetable.getCountByCoaches();

        Assertions.assertEquals(4, listOfCoaches.size());

        Assertions.assertEquals(coach, listOfCoaches.get(0).getCoach());
        Assertions.assertEquals(3,listOfCoaches.get(0).getCoachCountOfSessions());

        Assertions.assertEquals(coach2, listOfCoaches.get(3).getCoach());
        Assertions.assertEquals(1,listOfCoaches.get(3).getCoachCountOfSessions());
    }


}
