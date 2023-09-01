package com.ssafy.tott.member.domain.embbeded;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Password {
    @Column(nullable = false)
    private String value;
}
