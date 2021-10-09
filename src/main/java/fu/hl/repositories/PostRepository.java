package fu.hl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fu.hl.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query(value = "select * from post p where p.user_id in(\r\n"
			+ "select u.id from users u where u.username in (\r\n"
			+ "	select f.username from friend f, user_friend uf where uf.friend_id = f.id and uf.user_id = :id  \r\n"
			+ ")) order by p.created_date DESC", nativeQuery = true)
	public List<Post> getAllByUserId(@Param("id") long userId);
}
