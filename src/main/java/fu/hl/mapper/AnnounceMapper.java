package fu.hl.mapper;

import fu.hl.dto.AnnounceDTO;
import fu.hl.entity.Announce;

public class AnnounceMapper {
	public static AnnounceDTO _toDTO(Announce entity) {
		AnnounceDTO dto = new AnnounceDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setPost_id(entity.getPost().getId());
		dto.setFriend_id(entity.getFriend().getId());
		dto.setUser_id(entity.getUser().getId());
		return dto;
	}
}
