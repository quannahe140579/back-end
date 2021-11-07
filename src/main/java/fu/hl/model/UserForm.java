package fu.hl.model;

import lombok.Data;

@Data
public class UserForm {
	private String username;
    private String fullName;
    private String address;
    private String birthDate;
    private String phone;
    private byte[] avatar;
}
