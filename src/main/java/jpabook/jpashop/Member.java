package jpabook.jpashop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private long id;
    private String username;

}
