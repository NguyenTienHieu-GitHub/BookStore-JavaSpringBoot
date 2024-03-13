package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogModel {
	private int id;
	private String title;
	private String content;
	private String logo;
	private String uploadDay;
	private String banner;
	private boolean active;
	private String description;
	private String nameSearch;
}
