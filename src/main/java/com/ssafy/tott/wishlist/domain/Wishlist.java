package com.ssafy.tott.wishlist.domain;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.member.domain.Member;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wishlist_id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "house_id")
  private HouseDetail houseDetail;

  @Builder
  public Wishlist(Member member, HouseDetail houseDetail) {
    addRelatedMember(member);
    addRelatedByHouse(houseDetail);
  }

  private void addRelatedMember(Member member) {
    this.member = member;
    member.getWishlists().add(this);
  }

  private void addRelatedByHouse(HouseDetail houseDetail) {
    this.houseDetail = houseDetail;
    houseDetail.getWishlists().add(this);
  }

  public void removeRelated() {
    member.getWishlists().remove(this);
    houseDetail.getWishlists().remove(this);
  }
}
