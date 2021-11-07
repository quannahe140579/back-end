package fu.hl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fu.hl.entity.Announce;

public interface AnnouceRepository extends JpaRepository<Announce, Long>{
	@Modifying
	@Query(value = "insert into announce(title,friend_id,post_id,user_id)\r\n"
			+ "values (':title',:friendId,:post_id,:userId)",nativeQuery = true)
	public void saveAnnouce(@Param("title") String title, 
			@Param("post_id") long postId, 
			@Param("friendId") long friendId,
			@Param("userId") long userId);
}
