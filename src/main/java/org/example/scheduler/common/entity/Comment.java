package org.example.scheduler.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduler.comment.model.request.CreateCommentRequest;
import org.example.scheduler.comment.model.request.UpdateCommentRequest;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public static Comment of(CreateCommentRequest request, User user, Schedule schedule) {
        return new Comment(null, request.contents(), user, schedule);
    }

    public void updateComment(UpdateCommentRequest request) {
        this.contents = request.contents();
    }

}
