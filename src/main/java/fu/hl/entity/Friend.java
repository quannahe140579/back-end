package fu.hl.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.build.ToStringPlugin.Exclude;

@Data
@Entity
@Table(name = "[Friend]")
public class Friend{
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> listPos;
	
	@ManyToMany(mappedBy = "listFriend")
	@EqualsAndHashCode.Exclude
    @Exclude
	private List<User> listUser;
	
	@Column(name = "username", length = 30, nullable = false,unique = true)
	private String username;
	
	@OneToMany(mappedBy = "friend",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> listComment;
}
