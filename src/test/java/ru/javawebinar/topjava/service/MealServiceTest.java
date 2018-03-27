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
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

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
    public void get() throws NotFoundException {
        Meal meal = service.get(MEAL_AD1_ID, ADMIN_ID);
        assertMatch(meal, MEAL_AD1);
    }

    @Test
    public void delete() throws NotFoundException {
        service.delete(MEAL_AD1_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), MEAL_AD2);
    }

//    @Test
//    default List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
//        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
//    }

    @Test
    public void getBetweenDateTimes() {

    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(ADMIN_ID);
        assertMatch(all, MEAL_AD2, MEAL_AD1);
    }

    @Test
    public void update() throws NotFoundException {
        Meal updated = new Meal(MEAL_AD1);
        updated.setDescription("UpdatedMeal");
        updated.setCalories(100);
        updated.setDateTime(LocalDateTime.of(2018, Month.JUNE, 4, 12, 0));
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(updated.getId(), ADMIN_ID), updated);
    }

    @Test
    public void create() {

    }
}
