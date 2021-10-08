package fu.hl.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomeUserRepository {
	@Autowired
	private static EntityManager manager;
	
	public static void _getUserEntityByUserName() {

	}
}
