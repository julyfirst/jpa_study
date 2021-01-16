package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded // 내장타입 이기떄문에 쓰기
    private Address address;

    // 배송상태
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
