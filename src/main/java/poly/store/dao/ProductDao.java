/**
 * @(#)ProductDao.java 2021/10/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/10/10.
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Product;
import poly.store.model.StatisticalProductDay;

/**
 * Class thuc hien truy van thong tin bang Product trong database
 * 
 * @author KHOA-PH
 * @version 1.00
 */
public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();
	
	@Query(value="SELECT TOP(16) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY CreateDay DESC", nativeQuery = true)
	List<Product> getListLatestProduct();
	
	@Query(value="SELECT TOP(14) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListViewsProduct();
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);
	
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);
	
	@Query(value="SELECT TOP(10) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Cate_Id = ?1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);
	
	@Query(value="SELECT TOP(5) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Sales != 0 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductSales();
	
	@Query(value="SELECT * FROM ProductS WHERE NOT EXISTS (SELECT * FROM ORDERS WHERE Products.Id = ORDERS.Product_Id) AND ProductS.DeleteDay is NULL", nativeQuery = true)
	List<Product> listStatisticalProductWarehouse();
}
