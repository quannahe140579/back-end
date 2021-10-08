package fu.hl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fu.hl.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
