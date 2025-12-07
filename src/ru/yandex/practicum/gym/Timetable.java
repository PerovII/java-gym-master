package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();
        timetable.putIfAbsent(day, new TreeMap<>());
        timetable.get(day).putIfAbsent(time, new ArrayList<>());
        timetable.get(day).get(time).add(trainingSession);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    return timetable.getOrDefault(dayOfWeek, new TreeMap<>());
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap <TimeOfDay, ArrayList<TrainingSession>> trainingSessionsForDay = timetable.get(dayOfWeek);
        if (trainingSessionsForDay == null) return Collections.emptyList();
        if (trainingSessionsForDay.get(timeOfDay) == null) return Collections.emptyList();
        return trainingSessionsForDay.get(timeOfDay);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> counter = new HashMap<>();
        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingsForDay: timetable.values()) {
            for (ArrayList<TrainingSession> sessionsAtTime: trainingsForDay.values()) {
                for (TrainingSession session: sessionsAtTime) {
                    Coach coach = session.getCoach();
                    counter.put(coach, counter.getOrDefault(coach, 0) + 1);
                }
            }

        }
        List<CounterOfTrainings> counterOfTrainings = new ArrayList<>();
        CoachComparator comparator = new CoachComparator();
        for (Map.Entry<Coach, Integer> i: counter.entrySet()) {
            counterOfTrainings.add(new CounterOfTrainings(i.getKey(), i.getValue()));
        }
        counterOfTrainings.sort(comparator);
        return  counterOfTrainings;
    }

}
