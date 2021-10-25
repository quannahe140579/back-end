package fu.hl.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import fu.hl.dto.UserDTO;
import fu.hl.entity.Comment;
import fu.hl.entity.Friend;
import fu.hl.entity.Image;
import fu.hl.entity.Post;
import fu.hl.entity.User;
import fu.hl.mapper.UserMapper;
import fu.hl.model.LoginForm;
import fu.hl.model.RegisterForm;
import fu.hl.model.UserForm;
import fu.hl.repositories.FriendRepository;
import fu.hl.repositories.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserAPI {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FriendRepository friendRepository;
	
	
	@PostMapping(value = "/login")
	public UserDTO login(@RequestBody(required = true) LoginForm form) {
		User user = null;
		if (form != null) {
			if (!form.getUsername().isEmpty() && !form.getPassword().isEmpty()) {
				user = userRepository.findByUsernameAndPasswordAndIsActiveTrue(form.getUsername(), form.getPassword());
				
				if (user != null) {
					return UserMapper._toDTO(user);
				} else {
					return null;
				}
			}
		}
		return null;
	}

	@PostMapping(value = "/register")
	public UserDTO register(@RequestBody(required = true) RegisterForm form) {
		User user = null;
		User result = null;
		if (form != null) {
			if(!form.getUsername().isEmpty()) {
				user = userRepository.findByUsername(form.getUsername());
				if(user != null) {
					return null;
				}
			}
			if (!form.getUsername().isEmpty() && !form.getPassword().isEmpty() && !form.getRepassword().isEmpty()) {
				user = new User();
				user.setUsername(form.getUsername());
				user.setPassword(form.getPassword());
				user.setActive(true);
				
				user.setCreatedDate(new Date());
				
				result = userRepository.save(user);
				
				Friend f = _convertUserToFriend(user);
				friendRepository.save(f);
				
				if (result != null) {
					return UserMapper._toDTO(result);
				} else {
					return null;
				}
			}
		}
		return null;
	}
	@PostMapping("/profile")
	public UserDTO editProfile(@RequestBody(required = true) UserForm form) {
		User result = null;
		System.out.println(form.getBirthDate().toString());
		if(form != null) {
			User user = userRepository.findByUsername(form.getUsername());
			user.setAddress(form.getAddress());
			//user.setAvatar(form.getAvatar());
			user.setFullName(form.getFullName());
			user.setPhone(form.getPhone());
			user.setDateOfBirth(form.getBirthDate());
			result = userRepository.save(user);
			
			String fileLocation = new File("src\\main\\resources\\static\\uploads").getAbsolutePath() + "\\" + user.getUsername() + ".jpg";
			
			FileOutputStream output;
			try {
				output = new FileOutputStream(fileLocation);
				output.write(form.getAvatar());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return UserMapper._toDTO(result);
	}
	@GetMapping
	public UserDTO getUserByUsername(@RequestParam String username) {
		User u = userRepository.findByUsername(username);
		return UserMapper._toDTO(u);
	}
	
	@GetMapping(value = "/test")
	public UserDTO _generateUser() {
		if (userRepository.count() <= 0) {
			User u = _createUser();

			User u1 = _createUser();
			userRepository.save(u1);
			User u2 = _createUser();
			userRepository.save(u2);
			User u3 = _createUser();
			userRepository.save(u3);

			Friend f = _convertUserToFriend(u);
			Friend f1 = _convertUserToFriend(u1);
			Friend f2 = _convertUserToFriend(u2);
			Friend f3 = _convertUserToFriend(u3);
			friendRepository.save(f);
			friendRepository.save(f1);
			friendRepository.save(f2);
			friendRepository.save(f3);

			List<Friend> listFrU1 = new ArrayList<>();
			listFrU1.add(f1);
			listFrU1.add(f);
			listFrU1.add(f2);
			listFrU1.add(f3);
			u.setListFriend(listFrU1);

			Post p = _createPost(u);

			Image i = _createImage(p);
			List<Image> listImage = new ArrayList<>();
			listImage.add(i);
			p.setListImage(listImage);

			Comment c = _createComment(p);
			Comment c1 = _createComment(p);
			Comment c2 = _createComment(p);
			c.setFriend(f);
			c1.setFriend(f1);
			c2.setFriend(f2);
			List<Comment> listComment = new ArrayList<>();
			listComment.add(c);
			listComment.add(c1);
			listComment.add(c2);
			p.setListComment(listComment);

			Post p1 = _createPost(u);
			Post p2 = _createPost(u);

			List<Post> listPost = new ArrayList<>();
			listPost.add(p);
			listPost.add(p1);
			listPost.add(p2);
			u.setListPos(listPost);

			userRepository.save(u);
		}
		Optional<User> result = userRepository.findById(4L);

		return UserMapper._toDTO(result.orElse(null));
	}

	private Image _createImage(Post post) {
		Image image = new Image();
		image.setName("naq11.jpg");
		image.setPost(post);
		return image;
	}

	private Post _createPost(User u) {
		Faker faker = Faker.instance();
		Post post = new Post();
		post.setContent(faker.address().streetAddress());
		post.setActive(faker.random().nextBoolean());
		post.setCreatedDate(faker.date().birthday());
		post.setTotalLike(faker.random().nextInt(0, 200));
		post.setUser(u);
		return post;
	}

	private User _createUser() {
		Faker faker = Faker.instance();

		// create user first
		User createdUser = new User();

		createdUser.setCreatedDate(faker.date().birthday());
		createdUser.setActive(true);
		createdUser.setPassword("123");
		createdUser.setUsername(faker.name().username());
		createdUser.setAddress(faker.address().fullAddress());
		createdUser.setAvatar("naq11.jpg");
		createdUser.setDateOfBirth(faker.date().birthday());
		createdUser.setFullName(faker.name().fullName());
		createdUser.setPhone(faker.phoneNumber().cellPhone());

		return createdUser;
	}

	private Friend _convertUserToFriend(User u) {

		// create Friend first
		Friend createdUser = new Friend();
		createdUser.setUsername(u.getUsername());

		return createdUser;
	}

	private Comment _createComment(Post post) {
		Faker faker = Faker.instance();
		Comment comment = new Comment();
		comment.setContent(faker.university().name());
		comment.setPost(post);
		return comment;
	}


}
