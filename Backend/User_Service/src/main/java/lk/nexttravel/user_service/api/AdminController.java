package lk.nexttravel.user_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 21:57
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    AdminService adminService;

    //----------Save New Admin ------
    @PostMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Prepare(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Prepare(reqNewClientSaveDTO);
    }
    @PutMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Commit(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Commit(reqNewClientSaveDTO);
    }
    @DeleteMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Abrot(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Abrot(reqNewClientSaveDTO);
    }


    //----------Update Admin ------
    @PostMapping(value = "/update_admin")
    public ResponseEntity<String> SaveUpdatedAdmin_Prepare(@RequestBody ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO){
        return adminService.SaveUpdatedAdmin_Prepare(reqUpdateGuideAdminDTO);
    }
    @PutMapping(value = "/update_admin")
    public ResponseEntity<String> SaveUpdatedAdmin_Commit(@RequestBody ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO){
        return adminService.SaveUpdatedAdmin_Commit(reqUpdateGuideAdminDTO);
    }

    //get Profile Data - user admin
    @GetMapping(value = "/user-admin-get-profile-data")
    public ResponseEntity<ReqProfileDataAdminsDTO> userAdminGetProfileData(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileData(id,token);
    }
    //get profile image - vehicle admin
    @GetMapping(value = "/vehicle-admin-get-profile-image")
    public ResponseEntity<ReqProfileDataAdminsDTO> vehicleAdminGetProfileData(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileData(id,token);
    }

    //get profile image - hotel admin
    @GetMapping(value = "/hotel-admin-get-profile-image")
    public ResponseEntity<ReqProfileDataAdminsDTO> hotelAdminGetProfileData(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileData(id,token);
    }

    //get profile image - guide admin
    @GetMapping(value = "/guide-admin-get-profile-image")
    public ResponseEntity<ReqProfileDataAdminsDTO> guideAdminGetProfileData(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileData(id,token);
    }

}
