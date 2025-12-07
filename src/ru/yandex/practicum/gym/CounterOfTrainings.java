package ru.yandex.practicum.gym;

import java.util.Objects;

public class CounterOfTrainings {

    private final Coach coach;
    private int coachCountOfSessions;

    public  CounterOfTrainings (Coach coach, int coachCountOfSessions) {
        this.coach = coach;
        this.coachCountOfSessions = coachCountOfSessions;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getCoachCountOfSessions() {
        return coachCountOfSessions;
    }

}
