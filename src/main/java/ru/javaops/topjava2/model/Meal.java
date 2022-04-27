package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javaops.topjava2.util.DateTimeUtil;
import ru.javaops.topjava2.web.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

import static ru.javaops.topjava2.util.DateTimeUtil.asLocalDateTime;

/**
 * @author alex_jd on 4/19/22
 * @project topjava2
 */

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "description", "date_time"}, name = "meals_unique_restaurant_description_date_time_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Meal extends BaseEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    private Restaurant restaurant;

    public Meal(Meal meal) {
        this(meal.id, meal.dateTime, meal.description);
    }

    public Meal(Integer id, String description, Restaurant restaurant) {
        super(id);
        this.description = description;
        this.restaurant = restaurant;
        this.dateTime = LocalDateTime.now();
    }

    public Meal(String description, Restaurant restaurant) {
        this.description = description;
        this.restaurant = restaurant;
        this.dateTime = LocalDateTime.now();
    }

    public Meal(Integer id, LocalDateTime dateTime, String description) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
    }

}
