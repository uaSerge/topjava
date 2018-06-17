package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.to.UserTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {

    private MealsUtil() {
    }

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> Util.isBetween(meal.getTime(), startTime, endTime));
    }

    private static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(filter)
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
    public static Meal createNewFromMealWithExceed (MealWithExceed meal){
        return new Meal(meal.getId(),meal.getDateTime(),meal.getDescription(),meal.getCalories());
    }

    public static Meal updateFromMealWithExceed (Meal meal, MealWithExceed mealWithExceed) {
        meal.setDateTime(mealWithExceed.getDateTime());
        meal.setDescription(mealWithExceed.getDescription());
        meal.setCalories(mealWithExceed.getCalories());
        return meal;
    }
//    public static User updateFromTo(User user, UserTo userTo) {
//        user.setName(userTo.getName());
//        user.setEmail(userTo.getEmail().toLowerCase());
//        user.setPassword(userTo.getPassword());
//        return user;
//    }
}