package com.rakuten;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	//JPARepository will create the connection, prepare the statement, execute and close the connection.

}
