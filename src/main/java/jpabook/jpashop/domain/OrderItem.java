package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch=LAZY) //*->1(단방향)이므로 반대쪽에 mappedBy없음
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; //주문 수량
}
