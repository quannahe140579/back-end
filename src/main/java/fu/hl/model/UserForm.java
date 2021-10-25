package fu.hl.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserForm {
	private String username;
    private String fullName;
    private String address;
    private Date birthDate;
    private String phone;
    private byte[] avatar;
}
