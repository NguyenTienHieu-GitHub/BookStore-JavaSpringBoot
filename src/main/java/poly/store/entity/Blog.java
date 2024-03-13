package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blog implements Serializable {
//	thong tin blogs id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// thong tin ten title	
	private String title;
	
	// thong tin noi dung content	
	private String content;
	
	// thong tin logo
	private String logo;
	
	// Thong tin trang thai
	private boolean active;
	
	// Thong tin ngay khoi tao blog
	private String Uploadday;
	
	// Thong tin banner
	private String Banner;
	
	// Thong tin ten tim kiem
	private String Namesearch;
	
	// Thong tin mo ta
	private String description;
	
	// Thong tin ngay tao
	private String Createday;

	// Thong tin ma nguoi tao
	private int Personcreate;

	// Thong tin ngay xoa
	private String Deleteday;

	// Thong tin nguoi xoa
	private int Persondelete;

	// Thong tin ngay cap nhat
	private String Updateday;

	// Thong tin ma nguoi cap nhat
	private int Personupdate;
}
