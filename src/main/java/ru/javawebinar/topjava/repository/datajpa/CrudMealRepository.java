package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.T;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save(Meal meal);

    // false if meal do not belong to userId
    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
    void deleteMealByIdAAndUserId(@Param("id")int id, @Param("userId")int userId);

    // null if meal do not belong to userId
    @Override
    Optional<Meal> findById(Integer integer);


    // ORDERED dateTime desc

    @Transactional
    @Modifying
    @Query(name = Meal.ALL_SORTED)
    List<Meal> findAllById(@Param("userId")int userId);


    // ORDERED dateTime desc

    @Transactional
    @Modifying
    @Query(name = Meal.GET_BETWEEN)
    List<Meal> findAllByTimeBetween(@Param("userId")int userId,
                                    @Param("startDate")LocalDateTime startDate,
                                    @Param("endDate")LocalDateTime endDate);


}
