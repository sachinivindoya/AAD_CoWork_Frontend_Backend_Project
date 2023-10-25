package lk.nexttravel.apigateway.api;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 10:50
 */


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserController {
}
