package fu.hl.dto;

import lombok.Data;

@Data
public class ImageDTO {
	private long id;
	private String name;
	private byte[] data;
	private long post_id;
}
