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
    private int createMemberId;
    @CreatedDate
    @Column(nullable = false)
    private LocalDate createDate;
    @LastModifiedBy
    private int updateMemberId;
    @LastModifiedDate
    private LocalDate updateDate;
}
