package rocks.zipcode.blozzer.payload;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private String addedDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDto> comments=new HashSet<>();

}
