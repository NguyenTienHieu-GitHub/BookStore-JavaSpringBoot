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
 * Class thong tin truy van cua bang Information Shop
 * 
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopModel {
	private int id;
	private String name;
	private String time;
	private String phone;
	private String fax;
	private String email;
	private String logo;
	private String logoFooter;
	private String address;
	private boolean active;
}
