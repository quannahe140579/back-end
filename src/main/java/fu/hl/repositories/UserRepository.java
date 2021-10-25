package fu.hl.repositories;
import fu.hl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsernameAndPasswordAndIsActiveTrue(String username, String password);
	public User findByUsername(String username);
	
}
