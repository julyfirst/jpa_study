package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // Order table과 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 주문 가격
    private Long orderPrice;
    // 주문 수량
    private Long count;
//
//    protected OrderItem() {
//    }

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, Long orderPrice, Long count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // 비즈니스 로직

    public void cancel() {
        // 재고 수량을 원복 해준다
        getItem().addStock(count);
    }

    // 조회 로직
    /**
     * 주문상품 전체 가격 조회
     * @return
     */
    public Long getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
