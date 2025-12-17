package com.example.demo.service;

import com.example.demo.model.*;

import java.util.EnumMap;
import java.util.Map;

public class CompatibilityService {

    private static final int MAX_SCORE = 100;

    public double calculateCompatibility(HabitProfile a, HabitProfile b) {

        if (a.getStudentId() == b.getStudentId()) {
            throw new IllegalArgumentException("same student");
        }

        int score = 0;

        score += sleepScore(a.getSleepSchedule(), b.getSleepSchedule());
        score += studyScore(a.getStudyHoursPerDay(), b.getStudyHoursPerDay());
        score += enumScore(a.getCleanlinessLevel(), b.getCleanlinessLevel());
        score += enumScore(a.getNoiseTolerance(), b.getNoiseTolerance());
        score += socialScore(a.getSocialPreference(), b.getSocialPreference());

        return normalize(score);
    }

    /* ---------- Individual scorers ---------- */

    private int sleepScore(SleepSchedule s1, SleepSchedule s2) {
        return s1 == s2 ? 25 : 10;
    }

    private int studyScore(int h1, int h2) {
        int diff = Math.abs(h1 - h2);
        if (diff <= 1) return 20;
        if (diff <= 3) return 12;
        return 5;
    }

    private <E extends Enum<E>> int enumScore(E e1, E e2) {
        int gap = Math.abs(e1.ordinal() - e2.ordinal());
        if (gap == 0) return 15;
        if (gap == 1) return 8;
        return 3;
    }

    private int socialScore(SocialPreference a, SocialPreference b) {
        if (a == b) return 15;
        if (a == SocialPreference.BALANCED || b == SocialPreference.BALANCED) return 10;
        return 4;
    }

    private double normalize(int rawScore) {
        return Math.min(MAX_SCORE, rawScore);
    }
}
