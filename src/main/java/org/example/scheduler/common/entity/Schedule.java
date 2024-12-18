package org.example.scheduler.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduler.schedule.model.request.CreateScheduleRequest;
import org.example.scheduler.schedule.model.request.UpdateScheduleRequest;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String title;
    private String contents;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Builder
    public Schedule(String title, String contents, User author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public static Schedule of(CreateScheduleRequest request, User author) {
        return Schedule.builder()
            .title(request.title())
            .contents(request.contents())
            .author(author)
            .build();
    }

    public void updateSchedule(UpdateScheduleRequest request) {
        if(request.title() != null) {
            this.title = request.title();
        }
        if(request.contents() != null) {
            this.contents = request.contents();
        }
    }
}
