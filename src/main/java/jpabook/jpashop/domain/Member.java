package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy="member") //order table에 있는 member필드에 의해 mapping
    private List<Order> orders=new ArrayList<>(); //생성 후 컬렉션 절대 변경하지 말기(생성 시 hibernate에서 따로 관리함)
}
