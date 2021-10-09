package fu.hl.mapper;

import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import fu.hl.dto.UserDTO;
import fu.hl.entity.User;

public class UserMapper {
	public static UserDTO _toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setActive(entity.isActive());
		dto.setPassword(entity.getPassword());
		dto.setUsername(entity.getUsername());
		dto.setAddress(entity.getAddress());
		dto.setAvatar(entity.getAvatar());
		dto.setDateOfBirth(entity.getDateOfBirth());
		dto.setFullName(entity.getFullName());
		dto.setPhone(entity.getPhone());
		dto.setListPos(
				CollectionUtils.isEmpty(entity.getListPos()) ? null 
				: entity.getListPos().stream().map(p -> PostMapper._toDTO(p)).collect(Collectors.toList()) 
		);
		dto.setListFriend(
				CollectionUtils.isEmpty(entity.getListFriend()) ?
						 null : entity.getListFriend().stream().map( f -> FriendMapper._toDTO(f)).collect(Collectors.toList())
		);
		dto.setListAnnounce(
				CollectionUtils.isEmpty(entity.getListAnnounce()) ?
						 null : entity.getListAnnounce().stream().map( f -> AnnounceMapper._toDTO(f)).collect(Collectors.toList())
		);
		return dto;
	}
	
}
