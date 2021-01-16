package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이어노테이션 이패키지랑 이패키지 하위에있는것을 컴포넌트 스캔해서 스프링 빈으로 자동 등록
@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpashopApplication.class, args);
	}

}
