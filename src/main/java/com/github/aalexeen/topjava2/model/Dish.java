package com.github.aalexeen.topjava2.model;

import com.fasterxml.jackson.annotation.*;
import com.github.aalexeen.topjava2.util.DateTimeUtil;
import com.github.aalexeen.topjava2.web.View;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author alex_jd on 4/19/22
 * @project topjava2
 */

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"description", "date", "restaurant_id"}, name = "dish_unique_description_date_restaurant_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Dish extends BaseEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "date", nullable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    //@JsonIdentityReference(alwaysAsId = true)
    @NotNull//(groups = View.Persist.class)
    @ToString.Exclude
    private Restaurant restaurant;

    public Dish(Dish dish) {
        this(dish.id, dish.date, dish.description);
    }

    public Dish(Integer id, String description, Restaurant restaurant) {
        super(id);
        this.description = description;
        this.restaurant = restaurant;
        this.date = LocalDate.now();
    }

    public Dish(String description, Restaurant restaurant) {
        this.description = description;
        this.restaurant = restaurant;
        this.date = LocalDate.now();
    }

    public Dish(Integer id, LocalDate date, String description) {
        super(id);
        this.date = date;
        this.description = description;
    }

}
