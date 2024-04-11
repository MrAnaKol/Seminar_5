package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilterProductService {
	
	ArrayList<Product> filterByPriceLessThanThreshold(float threshold) throws Exception;
	ArrayList<Product> filterByQuantityLessThanThreshold(float threshold) throws Exception;
	ArrayList<Product> filterByTitleOrDescription(String text) throws Exception;
	float calculateProductTotalValue() throws Exception;

}
