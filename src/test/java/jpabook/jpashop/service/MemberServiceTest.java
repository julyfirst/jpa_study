package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

// junit 실행할 떄 스프링이랑 역어서 실행할래
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // commit을 안하고 롤백을해버림
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void signUp() throws Exception {
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long saveId = memberService.join(member);

        //then
        // em.flush();
        assertEquals(member,memberRepository.findOne(saveId));
        // 롤백을 해버리면 jpa 입장에서는 insert query를 날릴 필요가 없다.
    }

    @Test(expected = IllegalStateException.class)
    public void duplicate() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생


        //then
        fail("예외가 발생해야 함");
    }
    

     
}