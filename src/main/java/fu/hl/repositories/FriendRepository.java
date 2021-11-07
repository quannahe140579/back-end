package fu.hl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fu.hl.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long>{
	public Friend findByUsername(String username);
	public List<Friend> findByUsernameIgnoreCaseContaining(String data);
}
