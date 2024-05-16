package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	//SELECT * FROM PRODUCT_TABLE WHERE TITLE = title, DESCRIPTION=description, PRICE=price;
	//funkcijas atbilstošo ķermeni uzprogrammēs Data JPA mūsu vietā
	Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

	//funkcijas atbilstošo ķermeni Dat JPA uzprogrammēs mūsu vietā
	ArrayList<Product> findByPriceLessThan(float threshold);

	ArrayList<Product> findByQuantityLessThan(int threshold);

	ArrayList<Product> findByTitleContainingOrDescriptionContaining(String text, String text2);

	@Query(nativeQuery = true, value = "SELECT SUM(PRICE * QUANTITY) FROM PRODUCT_TABLE;")
	float calculateTotalValueFromDB();

}