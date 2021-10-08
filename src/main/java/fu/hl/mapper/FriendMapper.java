package fu.hl.mapper;

import fu.hl.dto.FriendDTO;
import fu.hl.entity.Friend;

public class FriendMapper {
	public static FriendDTO _toDTO(Friend entity) {
		FriendDTO dto = new FriendDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		return dto;
	}
}
