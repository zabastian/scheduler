package org.example.scheduler.schedule.repository;

import org.example.scheduler.common.entity.Schedule;
import org.example.scheduler.schedule.model.dto.SchedulePageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // TODO 하나의 쿼리에 댓글 개수와 원하는 스케줄의 정보를 가져오는 방법 JPA query method 만으로는 힘들다.

    @Query("SELECT new org.example.scheduler.schedule.model.dto.SchedulePageDto(" +
        "s.scheduleId, s.title, s.contents, u.userName, COUNT(c.commentId)) " +
        "FROM Schedule s " +
        "LEFT JOIN s.author u " +
        "LEFT JOIN Comment c ON c.schedule = s " +
        "WHERE u.userId = :userId " +  // 특정 사용자의 스케줄만 조회
        "GROUP BY s.scheduleId, s.title, s.contents, u.userName, s.updatedAt " +
        "ORDER BY s.updatedAt DESC"
    )
    Page<SchedulePageDto> findByAndAuthorUserId(long userId,Pageable pageable);
}
