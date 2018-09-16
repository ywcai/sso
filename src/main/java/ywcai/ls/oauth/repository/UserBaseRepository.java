package ywcai.ls.oauth.repository;


import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ywcai.ls.oauth.entity.UserBase;

 
@Repository
@Table(name="userbase")
@Qualifier("userRepository")
public interface UserBaseRepository extends JpaRepository<UserBase, Long > {

	UserBase findTop1ByUsername(String username);
 
}
