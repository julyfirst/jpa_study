package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// jpa 내장타입이기 떄문에 Embeddable 써줌 (어딘가에 내장 될수 있음) embeded 둘중 하나만 있어도됨
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {

    }

    // 생성할 때만 값이 정해지고
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
