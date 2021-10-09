package fu.hl.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.dto.PostDTO;
import fu.hl.entity.Post;
import fu.hl.mapper.PostMapper;
import fu.hl.repositories.PostRepository;

@RestController()
@RequestMapping("api/post")
public class PostAPI {
	@Autowired
	private PostRepository repository;
	
	@GetMapping
	public List<PostDTO> getAll(@RequestParam(name = "id") long userId){
		List<Post> listResult = null;
		listResult = repository.getAllByUserId(userId);	
		return PostMapper._toListDTO(listResult);
	}
	
}
