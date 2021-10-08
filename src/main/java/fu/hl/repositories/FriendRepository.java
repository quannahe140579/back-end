package fu.hl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fu.hl.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long>{
	
}
