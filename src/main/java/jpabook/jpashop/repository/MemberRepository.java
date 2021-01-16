package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// DAO랑 비슷
@Repository
public class MemberRepository {

    // 스프링부트가 엔티티매니저 주입을 해줌
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

     public Member find(Long id) {
        return em.find(Member.class, id);
     }


}
