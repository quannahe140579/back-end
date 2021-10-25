package fu.hl.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.constant.Constant;
import fu.hl.dto.PostDTO;
import fu.hl.entity.Post;
import fu.hl.mapper.PostMapper;
import fu.hl.repositories.PostRepository;

@RestController
@RequestMapping("api/post")
public class PostAPI {
	@Autowired
	private PostRepository repository;
	
	@GetMapping()
	@ResponseBody
	public List<PostDTO> getAll(@RequestParam(name = "id") long userId){
		List<Post> listResult = null;
		listResult = repository.getAllByUserId(userId);	
		return PostMapper._toListDTO(listResult);
	}
	
	@PutMapping("/like")
	@ResponseBody
	public int likePost(@RequestParam("postId") long postId,
			@RequestParam("type") int type) {
		Post p = repository.findById(postId).get();
		int totalLike = p.getTotalLike();
		if(p != null) {
			if(type == Constant.LIKE_UP) {
				totalLike += 1;
				p.setTotalLike(totalLike);	
			}else if(type == Constant.LIKE_DOWN) {
				totalLike -= 1;
				p.setTotalLike(totalLike);
			}
			repository.save(p);
		}
		return totalLike;
	}
}
