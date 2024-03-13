package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.store.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BestSellerModel {
	@Id
	private Product product;
	private long sum;
}
