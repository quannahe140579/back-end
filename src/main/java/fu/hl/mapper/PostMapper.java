package fu.hl.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import fu.hl.dto.PostDTO;
import fu.hl.entity.Post;

public class PostMapper {
	public static PostDTO _toDTO(Post entity) {
		PostDTO dto = new PostDTO();
		dto.setActive(entity.isActive());
		dto.setContent(entity.getContent());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setId(entity.getId());
		dto.setListComment(CollectionUtils.isEmpty(entity.getListComment()) ? null
				: entity.getListComment().stream().map(c -> CommentMapper._toDTO(c)).collect(Collectors.toList()));
		dto.setListImage(CollectionUtils.isEmpty(entity.getListImage()) ? null
				: entity.getListImage().stream().map(i -> ImageMapper._toDTO(i)).collect(Collectors.toList()));
		dto.setTotalLike(entity.getTotalLike());
		dto.setUser_id(entity.getUser().getId());
		return dto;
	}
	public static List<PostDTO> _toListDTO(List<Post> listEntity){
		List<PostDTO> result = new ArrayList<>();
		if(listEntity != null) {
			for (Post post : listEntity) {
				result.add(_toDTO(post));
			}
		}
		return result;
	}
}
