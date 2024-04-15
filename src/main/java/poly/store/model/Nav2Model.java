/**
 * @(#)EmployeeModel.java.
 *
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class thong tin truy van cua bang MenuOne
 * 
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nav2Model {
	private int id;
	private String name;
	private int nav1Id;
	private String nameSearch;
}
