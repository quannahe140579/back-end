package fu.hl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.dto.FriendDTO;
import fu.hl.entity.Friend;
import fu.hl.entity.User;
import fu.hl.mapper.FriendMapper;
import fu.hl.repositories.FriendRepository;
import fu.hl.repositories.UserRepository;

@RestController
@RequestMapping("/api/friend")
public class FriendAPI {
	@Autowired
	private FriendRepository friendRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityManager em;
	
	@GetMapping
	public List<FriendDTO> findFriendContain(@RequestParam("name") String name) {		
		List<Friend> list = friendRepository.findByUsernameIgnoreCaseContaining(name);
		List<FriendDTO> listDto = new ArrayList<>();
		for (Friend friend : list) {
			User u = userRepository.findByUsername(friend.getUsername());
			FriendDTO dto = FriendMapper._toDTO(friend);
			if(u.getAvatar() != null) {
				dto.setUrlAvt(u.getAvatar());
			}
			dto.setUrlAvt(u.getAvatar());
			dto.setFullName(u.getFullName());
			listDto.add(dto);
		}
		return listDto;
	}
	
	@Transactional
	@PostMapping
	public int addFriend(@RequestParam("user_id") long user_id,
			@RequestParam("friend_id") long friend_id) {
		String insertSql = "insert into user_friend(user_id, friend_id) values (?,?)\r\n"
				+ "insert into user_friend(user_id, friend_id) values (?,?)";
		Query query = em.createNativeQuery(insertSql);
		query.setParameter(1, user_id);
		query.setParameter(2,friend_id);
		query.setParameter(3, friend_id);
		query.setParameter(4,user_id);
		int num = query.executeUpdate();
		return num;
	}	
	
	@Transactional
	@PutMapping
	public int removeFriend(@RequestParam("user_id") long user_id,
			@RequestParam("friend_id") long friend_id) {
		String insertSql = "DELETE from user_friend where user_id = ? and friend_id = ?\r\n"
				+ " DELETE  from user_friend where user_id = ? and friend_id = ?";
		Query query = em.createNativeQuery(insertSql);
		query.setParameter(1, user_id);
		query.setParameter(2,friend_id);
		query.setParameter(3, friend_id);
		query.setParameter(4,user_id);
		int num = query.executeUpdate();
		return num;
	}
	
}
