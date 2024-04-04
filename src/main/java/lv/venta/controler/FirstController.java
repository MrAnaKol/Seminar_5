package lv.venta.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
	
	@GetMapping("/hello")//localhost:8080/hello
	public String getHello() {
		System.out.println("First Controller!!!");
		return "hello-page"; //tiek parādīta hello-page.html lapa
	}
}
