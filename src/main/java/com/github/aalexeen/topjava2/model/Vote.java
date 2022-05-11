package com.github.aalexeen.topjava2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.aalexeen.topjava2.util.DateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author alex_jd on 4/19/22
 * @project topjava2
 */

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "vote_unique_date_user_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    @NotNull//(groups = View.Persist.class)
    @ToString.Exclude
    //@JsonIgnoreProperties({"name", "email", "enabled", "registered", "roles"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    @NotNull//(groups = View.Persist.class)
    @ToString.Exclude
    //@JsonIgnoreProperties({"name", "registered", "meals"})
    private Restaurant restaurant;

    @Column(name = "date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @JsonFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate localDate = LocalDate.now();
            //asLocalDateTime(new Date()).toLocalDate();

    @Column(name = "time", nullable = false, columnDefinition = "time default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = DateTimeUtil.TIME_PATTERN)
    @JsonFormat(pattern = DateTimeUtil.TIME_PATTERN)
    private LocalTime localTime = LocalTime.now();
            //asLocalDateTime(new Date()).toLocalTime();

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate localDate, LocalTime localTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.localDate = localDate;
        this.localTime = localTime;
    }
}
