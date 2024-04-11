package lv.venta.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImp implements ICRUDProductService, IFilterProductService{

	private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(
			new Product("Abols", "Zilgan-zaļš", 0.99f, 5),
			new Product("Zemene", "Salda", 1.99f, 50), 
			new Product("Burkans", "Oranžš", 0.39f, 500)));
	@Override
	public ArrayList<Product> filterByPriceLessThanThreshold(float threshold) throws Exception {
		if (threshold < 0 || threshold > 10000) throw new Exception("The limit of price is of bounds");
		
		ArrayList<Product> result = new ArrayList<>();
		
		for(Product tempP: allProducts) {
			if(tempP.getPrice() < threshold) {
				result.add(tempP);
			}
		}
		
		return result;
	}

	@Override
	public ArrayList<Product> filterByQuantityLessThanThreshold(float threshold) throws Exception {
		if (threshold < 0 || threshold > 10000) throw new Exception("The limit of quantity is of bounds");
		
		ArrayList<Product> result = new ArrayList<>();
		
		for(Product tempP: allProducts) {
			if(tempP.getQuantity() < threshold) {
				result.add(tempP);
			}
		}
		
		return result;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String text) throws Exception {
		if (text == null) throw new Exception("Search text can not be null");
		
		ArrayList<Product> result = new ArrayList<>();
		
		for(Product tempP: allProducts) {
			if(tempP.getTitle().contains(text) || tempP.getDescription().contains(text)) {
				result.add(tempP);
			}
		}
		
		return result;
	}

	@Override
	public float calculateProductTotalValue() throws Exception {
		if (allProducts.isEmpty())
			throw new Exception("Product list is empty");
		
		float result = 0;
		
		for(Product tempP: allProducts) {
			result += tempP.getPrice()*tempP.getQuantity();
		}
		
		return result;
	}

	@Override
	public Product create(String title, String description, float price, int quantity) throws Exception {
		if(title==null || description==null || price < 0 || quantity < 0)
			throw new Exception("Problems with input params");
		
		for(Product tempP: allProducts) {
			if(tempP.getTitle().equals(title) && tempP.getDescription().equals(description) && tempP.getPrice() == price) {
				tempP.setQuantity(tempP.getQuantity() + quantity);
				return tempP;
			}
		}
		
		Product newProduct = new Product(title, description, price, quantity);
		allProducts.add(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(allProducts.isEmpty())
			throw new Exception("No products");
		
		ArrayList<Product> allProd = new ArrayList<>();
		
		for(Product tempP: allProducts) {
			allProd.add(tempP);
		}
		return allProd;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if(id <= 0)
			throw new Exception("ID needs to be bigger than 0");
		for(Product tempP: allProducts) {
			if(tempP.getId() == id) {
				return tempP;
			}
		}
		throw new Exception("Product with this ID not found");
	}

	@Override
	public void updateByID(int id, String title, String description, float price, int quantity) throws Exception {
		
		if(title == null || description == null || price < 0 || price > 10000 || quantity < 0 || quantity > 10000)
			throw new Exception("Invalid input data");
		Product productToUpdate = retrieveById(id);
		productToUpdate.setTitle(title);
		productToUpdate.setDescription(description);
		productToUpdate.setPrice(price);
		productToUpdate.setQuantity(quantity);
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		Product deleteProduct = retrieveById(id);
		allProducts.remove(deleteProduct);

	}

}
