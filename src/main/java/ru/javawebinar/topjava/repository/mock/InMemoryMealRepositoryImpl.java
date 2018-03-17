package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(),(id,oldMeal) -> meal);
    }

    @Override
    public Boolean delete(int id) {
        return repository.remove(id)!= null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    public List<Meal> getAllf() {
        return repository.values().stream().
                filter(meal -> meal.getUserId() == AuthorizedUser.id()).
                sorted((meal1,meal2)-> meal2.getDateTime().compareTo(meal1.getDateTime())).
                collect(Collectors.toList());
    }
    @Override
    public List<Meal> getAll() {
        return repository.values().stream().
//                filter(meal -> meal.getUserId() == AuthorizedUser.id()).
                sorted((meal1,meal2)-> meal2.getDateTime().compareTo(meal1.getDateTime())).
                collect(Collectors.toList());
    }

}