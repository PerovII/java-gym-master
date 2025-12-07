package ru.yandex.practicum.gym;

import java.util.Comparator;

public class CoachComparator implements Comparator<CounterOfTrainings> {

    @Override
    public int compare(CounterOfTrainings coach1, CounterOfTrainings coach2) {
        return coach2.getCoachCountOfSessions() - coach1.getCoachCountOfSessions();
    }
}
