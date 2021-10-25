package fu.hl.mapper;

import fu.hl.dto.CommentDTO;
import fu.hl.entity.Comment;

public class CommentMapper {
	public static CommentDTO _toDTO(Comment entity) {
		CommentDTO dto = new CommentDTO();
		dto.setId(entity.getId());
		dto.setPost_id(entity.getPost().getId());
		dto.setContent(entity.getContent());
		dto.setFriendName(entity.getFriend().getUsername());
		return dto;
	}
}
