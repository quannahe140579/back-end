package fu.hl.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.build.ToStringPlugin.Exclude;

@Data
@Entity
@Table(name = "[Friend]")
public class Friend extends Account{
	
	@Column(name = "full_name", length = 30)
	private String fullName;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> listPos;
	
	@ManyToMany(mappedBy = "listFriend")
	@EqualsAndHashCode.Exclude
    @Exclude
	private List<User> listUser;

	@Lob
	@Column(name = "avatar")
	private byte[] avatar;
}
