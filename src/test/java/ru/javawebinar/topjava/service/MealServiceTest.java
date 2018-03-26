package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    void get(int id, int userId) throws NotFoundException {
        Meal meal = service.get(MEAL_AD1.getId(), ADMIN_ID);
        assertMatch(meal, MEAL_AD1);
    }

    @Test
    void delete(int id, int userId) throws NotFoundException {
        service.delete(MEAL_AD1.getId(), ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), MEAL_AD2);
    }

//    @Test
//    default List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
//        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
//    }

    @Test
    List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }

    @Test
    void getAll(int userId) {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_US2, MEAL_US1, MEAL_AD2, MEAL_AD1);
    }

    @Test
    void update(Meal meal, int userId) throws NotFoundException {
        Meal updated = new Meal(MEAL_AD1);
        updated.setDescription("UpdatedMeal");
        updated.setCalories(100);
        updated.setDateTime(LocalDateTime.of(2018, Month.JUNE, 4, 12, 0));
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(updated.getId(), ADMIN_ID), updated);
    }

    @Test
    Meal create(Meal meal, int userId) {
        return null;
    }
}
