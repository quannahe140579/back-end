package fu.hl.api;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.dto.CommentDTO;
import fu.hl.entity.Friend;
import fu.hl.repositories.FriendRepository;

@RestController
@RequestMapping("/api/comment")
public class CommentAPI {
	@Autowired
	private EntityManager em;

	@Autowired
	private FriendRepository fRepo;
	
	@PostMapping
	@Transactional
	public void insertComment(@RequestBody CommentDTO cDto) {
		if(cDto != null) {		
			Friend f = fRepo.findByUsername(cDto.getFriendName());
			Query query = em.createNativeQuery("insert into comment(content,friend_id,post_id) values (?,?,?)");
			query.setParameter(1, cDto.getContent());
			query.setParameter(2, f.getId());
			query.setParameter(3, cDto.getPost_id());
			query.executeUpdate();
		}
	}
}
