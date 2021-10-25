package fu.hl.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
  private long id;
  private String username;
  private String password;
  private boolean isActive;
  private Date createdDate;
  private String fullName;
  private Date dateOfBirth;
  private String phone;
  private String address;
  private List<FriendDTO> listFriend;
  private List<PostDTO> listPos;
  private String avatar;
  private List<AnnounceDTO> listAnnounce;
}
