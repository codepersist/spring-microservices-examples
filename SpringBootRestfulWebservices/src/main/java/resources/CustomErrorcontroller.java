package resources;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 
 * To achieve the custom error logic for our spring boot application, we must create a custom error controller 
 * that will implement the "ErrorController" interface. 
 * This interface provides a "getErrorPath()" method that we will override to return a custom path 
 * that will be called when an error occurs.
 * 
 */
@Controller
public class CustomErrorcontroller implements ErrorController {

	private static final String ERROR_PATH = "/error";

	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@GetMapping(value= ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        //LOGGER.info("Showing the custom error page.");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if(status!=null) {
        	Integer statusCode = Integer.valueOf(status.toString());
        	if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            }
        	else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
        	{
        		return "500";
        	}
        }
        return "error";       // In case of an error, this custom error page (404.html) will be rendered.
    }

}
