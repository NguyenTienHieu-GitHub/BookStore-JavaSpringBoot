/**
 * @(#)CategoryModel.java.
 *
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class thong tin truy van cua bang Category
 * 
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;
	private String name;
	private String banner;
	private String logo;
	private String describe;
	private String nameSearch;
}
