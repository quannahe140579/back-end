package fu.hl.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "Post")
@Data
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "content")
	private String content;
	
	@OneToMany(targetEntity = Image.class,mappedBy = "post", cascade = CascadeType.ALL)
	private List<Image> listImage;
	
	@Column
	@OneToMany(targetEntity = Comment.class,mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> listComment;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "total_like")
	private int totalLike;
	
	@Column(name = "active")
	private boolean isActive;
}
