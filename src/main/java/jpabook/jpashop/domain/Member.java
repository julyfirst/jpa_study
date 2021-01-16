package jpabook.jpashop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String username;

    @Embedded // 하나의 객체로써 사용하려면
    private Address address;

    // member랑 order는 1:N 관계
    // order table에 있는 member에 정의가 된거야(읽기전용)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
