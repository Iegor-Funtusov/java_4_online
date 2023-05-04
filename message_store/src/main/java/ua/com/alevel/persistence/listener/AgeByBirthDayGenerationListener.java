package ua.com.alevel.persistence.listener;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import ua.com.alevel.persistence.entity.user.Personal;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class AgeByBirthDayGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateAgeByBirthDay(Personal user) {
        if (user.getBirthDay() == null) {
            user.setAge(null);
            return;
        }
        Years years = Years.yearsBetween(new LocalDate(user.getBirthDay()), new LocalDate());
        user.setAge(years.getYears());
    }
}
