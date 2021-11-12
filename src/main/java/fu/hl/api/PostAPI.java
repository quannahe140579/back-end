package fu.hl.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fu.hl.constant.Constant;
import fu.hl.dto.PostDTO;
import fu.hl.entity.Image;
import fu.hl.entity.Post;
import fu.hl.entity.User;
import fu.hl.mapper.PostMapper;
import fu.hl.model.PostForm;
import fu.hl.repositories.PostRepository;
import fu.hl.repositories.UserRepository;

@RestController
@RequestMapping("api/post")
public class PostAPI {
	@Autowired
	private PostRepository repository;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");

	@Autowired
	private UserRepository userRepository;
	@GetMapping
	@ResponseBody
	public List<PostDTO> getAll(@RequestParam(name = "id") long userId){
		List<Post> listResult = null;
		listResult = repository.getAllByUserId(userId);	
		if(listResult != null) {
			for(int i = 0; i < listResult.size(); i++) {
				if(!listResult.get(i).isActive()) {
					listResult.remove(i);
					i--;
				}
			}
		}
		List<PostDTO> listDto = PostMapper._toListDTO(listResult);
		return listDto;
	}
	
	@Transactional
	@PostMapping("/create")
	public PostDTO createPost(@RequestBody PostForm dto) {
		User u = userRepository.findById(dto.getUser_id()).get();
		Date d = new Date();
		String createDateStr = sdf.format(d);
		Post postEntity = new Post();
		postEntity.setActive(true);
		postEntity.setContent(dto.getContent());
		postEntity.setTotalLike(0);
		postEntity.setCreatedDate(d);
		postEntity.setUser(u);
		
		writeFileToStaticFolder(dto.getByteAvt(), u.getUsername() + createDateStr);
		
		Post p = repository.save(postEntity);
		
		Image img = new Image();
		img.setName(u.getUsername() + createDateStr + ".jpg");
		img.setPost(p);
		List<Image> listImg = new ArrayList<Image>();
		listImg.add(img);
		p.setListImage(listImg);
		return PostMapper._toDTO(p);
	}
	@Transactional
	@PutMapping("/update")
	public PostDTO updatePost(@RequestBody PostForm dto) {
		User u = userRepository.findById(dto.getUser_id()).get();
		Date d = new Date();
		String createDateStr = sdf.format(d);
		
		Post postEntity = repository.findById(dto.getPostId()).get();
		
		writeFileToStaticFolder(dto.getByteAvt(), u.getUsername() + createDateStr);
		
		Image img = new Image();
		img.setName(u.getUsername() + createDateStr + ".jpg");
		img.setPost(postEntity);
		List<Image> listImg = new ArrayList<Image>();
		listImg.add(img);
		postEntity.setListImage(listImg);
		Post p = repository.save(postEntity);
		return PostMapper._toDTO(p);
	}
	
	@PutMapping("/delete")
	public void removePostById(@RequestParam("postId") long postId) {	
		Post post = repository.findById(postId).get();
		if(post != null) {
			post.setActive(false);
		}
		repository.save(post);
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
	private void writeFileToStaticFolder(byte[] data, String fileName) {
		String fileLocation = new File("src\\main\\resources\\static\\uploads").getAbsolutePath() + "\\" + fileName + ".jpg";
		FileOutputStream output;
		try {
			output = new FileOutputStream(fileLocation);
			output.write(data);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
