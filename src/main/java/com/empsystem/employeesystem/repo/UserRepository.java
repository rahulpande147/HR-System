package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.specification.UsersSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <Users, Long>, JpaSpecificationExecutor<Users> {
    List<Users> findByProjectDetailsProjectid(Long projectid);

   /*@Query ("SELECT a FROM Users a WHERE a.fname = ?1 AND a.lname = ?2")
    List<Users> findByFnameAndLname(String fname, String lanme);

   @Query ("FROM Users where fname = ?1")
    List<Users> findByFirstName(String fname);*/

   /*@Query("select s from Users s where s.fname or s.lname or s.username like %?1%")
   Page<Users> findByWord (String fname, Pageable pageable);*/


}
