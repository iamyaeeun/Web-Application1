package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="orders") //적어주지 않으면 관례상 order가 되는데, 쿼리와 같아 오류남
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="member_id") //연관관계 주인 //연관관계 주인의 값이 변경됐을 때 FK 값(member_id)을 변경
    private Member member;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL) //order persist시 orderItem도 persist해줌
    private List<OrderItem> orderItems=new ArrayList<>();

    @OneToOne(fetch=LAZY, cascade=CascadeType.ALL) //order persist시 delivery도 persist해줌
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태

    //==연관관계 메서드==//
    public void setMember(Member member){ //연관관계 메서드는 양방향에서 사용
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }
}
