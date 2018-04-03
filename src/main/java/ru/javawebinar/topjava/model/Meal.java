package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND user_id=?1"),
        @NamedQuery(name = Meal.ALL_SORTED_DATE, query = "SELECT m FROM Meal m LEFT JOIN FETCH User AS user_id WHERE user_id=?1  AND date_time >= ?2 AND date_time <= ?3 ORDER BY date_time DESC"),
//      @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m  LEFT JOIN FETCH m.user WHERE user_id=?1 ORDER BY date_time DESC"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user_id=:userId ORDER BY m.date_time DESC")
})
@Entity
@Table(name = "meals", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")
})
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED_DATE = "Meal.getAllSorted";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false)
    private int calories;

    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @ElementCollection(fetch = FetchType.EAGER)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}