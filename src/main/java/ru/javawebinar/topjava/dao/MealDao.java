package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealDao {

    void addMeal(Meal meal);

    void updateMeal(Meal book);

    void removeMeal(int id);

    List<MealWithExceed> getListMealWithExceeded();
}
