package fu.hl.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User extends Account {
	@Column(name = "full_name", length = 30)
	private String fullName;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@ManyToMany(targetEntity = Friend.class, cascade = CascadeType.ALL)
	@JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Friend> listFriend;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> listPos;

	@Lob
	@Column(name = "avatar")
	private byte[] avatar;

	@OneToMany(mappedBy = "user",targetEntity = Announce.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Announce> listAnnounce;

}
