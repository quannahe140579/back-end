package fu.hl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {
	
	@RequestMapping(value = "/home")
	public String homePage() {
		
		return "Hello world";
	}
}
