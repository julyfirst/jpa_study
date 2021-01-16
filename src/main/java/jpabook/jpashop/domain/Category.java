package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 내부모니까 타입을 넣어주고
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 자식은 카테고리를 여러개 가질 수 있음
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}