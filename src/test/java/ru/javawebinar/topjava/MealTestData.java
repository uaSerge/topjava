package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int  MEAL_AD1_ID = START_SEQ+2;
    public static final int  MEAL_AD2_ID = START_SEQ+3;
    public static final int  MEAL_US1_ID = START_SEQ+4;
    public static final int  MEAL_US2_ID = START_SEQ+5;

    public static final Meal MEAL_AD1 =  new Meal(LocalDateTime.of(2015, Month.JUNE, 4, 12, 0), "Админ ланч", 510);
    public static final Meal MEAL_AD2 =  new Meal(LocalDateTime.of(2015, Month.JUNE, 4, 22, 0), "Админ ужин", 1500);
    public static final Meal MEAL_US1 =  new Meal(LocalDateTime.of(2016, Month.JULY, 4, 12, 0), "User ланч", 510);
    public static final Meal MEAL_US2 =  new Meal(LocalDateTime.of(2016, Month.JULY, 4, 22, 0), "User ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }
}
