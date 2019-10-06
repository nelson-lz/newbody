package py.edu.fpune.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

	public static final String EXCEPTIONS = "/exceptions";
	public static final String MY_FILTER = "/my-filter";
	public static final String OUT_OF_TIME = "/out-of-time";
	public static final String ID = "/{id}";
	public static final String ERROR = "/error";
	
	@GetMapping(value=MY_FILTER)
	public String myFilter() {
		return "{\"state\":\"my-filter\"}";
	}
	
	@GetMapping(value = OUT_OF_TIME)
	public String outOfTime() {
		return "{\"state\":\"off\"}";
	}
	
	
}
