package fu.hl.api;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.dto.AnnounceDTO;
import fu.hl.repositories.AnnouceRepository;

@RestController
@RequestMapping("/api/announce")
public class AnnounceAPI {
	@Autowired
	AnnouceRepository repository;
	@Autowired
	EntityManager em;
	
	@Transactional
	@PostMapping
	public void createAnnounce(@RequestBody(required = true) AnnounceDTO dto) {
		Query q = em.createNativeQuery("insert into announce(title,friend_id,post_id,user_id)\r\n"
				+ "values (?,?,?,?)");
		q.setParameter(1, dto.getTitle());
		q.setParameter(2, dto.getFriend_id());
		q.setParameter(3, dto.getPost_id());
		q.setParameter(4, dto.getUser_id());
		q.executeUpdate();
	}
	
	@Transactional
	@PutMapping
	public int deleteAnnouceByUserIdAndFriendId(@RequestParam("user_id") long user_id,
			@RequestParam("friend_id") long friend_id) {
		Query query = em.createNativeQuery("delete from announce where user_id = ? and friend_id = ?");
		query.setParameter(1, user_id);
		query.setParameter(2, friend_id);
		int num = query.executeUpdate();
		return num;
	}
}
