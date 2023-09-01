package com.ssafy.tott.global.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedBy
    @Column(nullable = false)
    private long createdMemberId;
    @CreatedDate
    @Column(nullable = false)
    private LocalDate createdDate;
    @LastModifiedBy
    private long updatedMemberId;
    @LastModifiedDate
    private LocalDate updatedDate;
}
