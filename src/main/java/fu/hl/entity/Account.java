package fu.hl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public class Account {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", length = 30, nullable = false,unique = true)
	private String username;
	
	@Column(name = "password", length = 30, nullable = false)
	private String password;
	
	@Column(name = "active",nullable = false)
	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;
}
