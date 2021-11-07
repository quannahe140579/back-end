package fu.hl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fu.hl.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}
