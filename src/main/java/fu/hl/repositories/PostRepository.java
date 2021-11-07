package fu.hl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fu.hl.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query(value = "select * from post where user_id in (\r\n"
			+ "	select friend_id from user_friend where user_id = :id\r\n"
			+ ") or user_id = :id order by created_date DESC", nativeQuery = true)
	public List<Post> getAllByUserId(@Param("id") long userId);

}
