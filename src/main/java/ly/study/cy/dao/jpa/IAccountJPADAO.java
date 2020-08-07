package ly.study.cy.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ly.study.cy.bean.Account;

public interface IAccountJPADAO extends JpaRepository<Account, Integer> {

}
