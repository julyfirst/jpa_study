package jpabook.jpashop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 연관관계 주인 member와 order 중에서는 order가 주인
@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long id;

    // order랑 member는 N:1관계
    // 여기에 값을 셋팅하면 member_id fk 값이 다른것으로 변경됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK Id 설정
    private Member member;

    // OrderItem table과 1:N 관계
    // 주문상품
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 주문시간
    private LocalDateTime orderDateTime;

    // 주문상태 [ORDER, CANCEL]
    @Enumerated(EnumType.STRING) // 반드시 이걸로 선언해야함
    private OrderStatus status;

    // 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
