package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealDaoImpl implements MealDao {
    private static MealDaoImpl instance = new MealDaoImpl();
    private List<Meal> listMeal;

    private MealDaoImpl() {
        listMeal = new CopyOnWriteArrayList<>();
        listMeal.addAll(Arrays.asList(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)));
    }

    public static MealDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void addMeal(Meal meal) {
        listMeal.add(meal);
    }

    @Override
    public void updateMeal(Meal book) {

    }

    @Override
    public void removeMeal(int id) {
       listMeal.removeIf(meal -> meal.getID() == id);
    }

    @Override
    public List<MealWithExceed> getListMealWithExceeded() {
        return MealsUtil.getMealsWithExceeded(listMeal);
    }
}
