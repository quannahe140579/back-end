package fu.hl.dto;

import lombok.Data;

@Data
public class CommentDTO {
	private long id;
	private long post_id;
	private String content;
	private String friendName;
}
