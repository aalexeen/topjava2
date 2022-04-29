package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javaops.topjava2.util.DateTimeUtil;
import ru.javaops.topjava2.web.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static ru.javaops.topjava2.util.DateTimeUtil.asLocalDateTime;

/**
 * @author alex_jd on 4/19/22
 * @project topjava2
 */

@Entity
@Table(name = "voting", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "meals_unique_date_user_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Voting extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    @JsonIgnoreProperties({"name", "email", "enabled", "registered", "roles"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    @JsonIgnoreProperties({"name", "registered", "meals"})
    private Restaurant restaurant;

    @Column(name = "date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @JsonFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate localDate = asLocalDateTime(new Date()).toLocalDate();

    @Column(name = "time", nullable = false, columnDefinition = "time default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = DateTimeUtil.TIME_PATTERN)
    @JsonFormat(pattern = DateTimeUtil.TIME_PATTERN)
    private LocalTime localTime = asLocalDateTime(new Date()).toLocalTime();

    public Voting(Integer id, User user, Restaurant restaurant, LocalDate localDate, LocalTime localTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.localDate = localDate;
        this.localTime = localTime;
    }
}
