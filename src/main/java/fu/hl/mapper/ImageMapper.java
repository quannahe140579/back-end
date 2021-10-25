package fu.hl.mapper;

import fu.hl.dto.ImageDTO;
import fu.hl.entity.Image;

public class ImageMapper {
	public static ImageDTO _toDTO(Image entity) {
		ImageDTO dto = new ImageDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPost_id(entity.getPost().getId());
		return dto;
	}
}
