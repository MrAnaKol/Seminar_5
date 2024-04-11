package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface ICRUDProductService {
	
	Product create(String title, String description, float price, int quantity) throws Exception;
	ArrayList<Product> retrieveAll() throws Exception;
	Product retrieveById(int id) throws Exception;
	void updateByID(int id, String title, String description, float price, int quantity) throws Exception;
	void deleteById(int id) throws Exception;
	
}
