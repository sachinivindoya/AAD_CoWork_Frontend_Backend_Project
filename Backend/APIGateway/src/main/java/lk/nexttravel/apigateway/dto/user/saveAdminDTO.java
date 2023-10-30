package lk.nexttravel.apigateway.dto.user;

import lk.nexttravel.apigateway.util.RoleTypes;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 16:10
 */
@Component
public class saveAdminDTO {


    private String id;

    private String name;  //uniq

    private String email;

    private RoleTypes role_type;

    private String signup_name_with_initial;

    private String nic_or_passport;

    private String address;

    private long salary;

    private String profile_image;
}
