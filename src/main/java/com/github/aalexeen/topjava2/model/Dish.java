package com.github.aalexeen.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    public Dish(Dish dish) {
        this(dish.id, dish.date, dish.description);
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
