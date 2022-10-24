package com.kimeli.carsproject;

import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
/*@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)*/
class CarsProjectApplicationTests {

//	@Autowired
//	private UsersRepository usersRepository;
	/*@Autowired
	private TestEntityManager entityManager;*/
	@Test
	void contextLoads() {
	}

	/*@Test
	public void testUsers(){
		Users users = new Users();
		users.setFirstname("Dennis");
		users.setLastname("Rono");
		users.setIdnumber(812345678L);
		Users saveUser=usersRepository.save(users);*/
		/*Users existUser=entityManager.find(Users.class,saveUser.getId());
		assert existUser.getIdnumber() == existUser.getIdnumber();*/
	//}

}
