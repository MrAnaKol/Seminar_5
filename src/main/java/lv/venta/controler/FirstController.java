package lv.venta.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Controller
public class FirstController {
	
	@Autowired
	private ICRUDProductService crudService;
	
	@Autowired
	private IFilterProductService filterService;
	
	@GetMapping("/hello")//localhost:8080/hello
	public String getHello() {
		System.out.println("First Controller!!!");
		return "hello-page"; //tiek parādīta hello-page.html lapa
	}
	
	@GetMapping("/hello/msg")//localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
		System.out.println("Msg controller is called");
		model.addAttribute("mydata", "Ziņa no JAVA Spring!!!");
		return "hello-msg-page"; // tiek parādīra hello-msg-page.html lapa
	}
	
	@GetMapping("/product/test")//localhost:8080/product/test
	public String getProductTest(Model model) {
		Product tempProduct = new Product("Abols", "Sarkans", 0.99f, 5);
		model.addAttribute("mydata", tempProduct);
		return "product-one-show-page";//tiek parādīta product-one-show-page.html lapa
	}
	
	@GetMapping("/product/test2")//localhost:8080/product/test2
	public String getProductTest2(Model model) {
		try {
			model.addAttribute("mydata", crudService.retrieveAll());
			return "product-array-show-page";//
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page"; // tiek parādīta error.page.html lapa
		}
		
	}
	
	@GetMapping("/product/one") //localhost:8080/product/one?id=5
	public String getProductOneId(@RequestParam("id")int id, Model model) {
		try {
			model.addAttribute("mydata", crudService.retrieveById(id));
			return "product-one-show-page";//tiek parādīta product-one-show-page.html lapa
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page"; // tiek parādīta error.page.html lapa
		}
	}
	
	@GetMapping("/product/all/{id}") //localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id")int id, Model model) {
		try
		{
			model.addAttribute("mydata", crudService.retrieveById(id));
			return "product-one-show-page";// tiek parādīta product-one-show-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
	}
	
	@GetMapping("/product/insert") //localhost:8080/product/insert
	public String getProductInsert(Model model) {
		model.addAttribute("product", new Product());
		return "product-insert-page";
	}
	
	@PostMapping("/product/insert")
	public String postProductInsert(@Valid Product product, BindingResult result) {//iegūstam aju aizpildītu produktu
		//sajā gadījumā ir validāciju pāŗkāpumi Product objektam
		if(result.hasErrors()) {
			return "product-insert-page";//paliekam šajā pašā lapā
		}
		else
		{
			try {
				crudService.create(product.getTitle(), product.getDescription(), 
					product.getPrice(), product.getQuantity());
				return "redirect:/product/test2";//tiks pārvirzīts jeb izsaukts localhost:8080/product/all
			} catch (Exception e) {
			
				return "redirect:/error";//tiks pārvirzīts jeb izsaukt loclahost:8080/error
			}
		}
	
	}
	
	@GetMapping("/error") //localhost:8080/error
	public String getError() {
		return "error-page";
	}
}
