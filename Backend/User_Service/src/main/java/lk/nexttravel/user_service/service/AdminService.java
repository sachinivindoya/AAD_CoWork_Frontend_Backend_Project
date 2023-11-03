package lk.nexttravel.user_service.service;

import lk.nexttravel.user_service.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_service.dto.ReqProfileDataAdminsDTO;
import lk.nexttravel.user_service.dto.ReqUpdateGuideAdminDTO;
import lk.nexttravel.user_service.entity.Admin;
import org.springframework.http.ResponseEntity;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:35
 */
public interface AdminService {

    ResponseEntity<String> SaveNewClient_Prepare(ReqNewClientSaveDTO reqNewClientSaveDTO);

    ResponseEntity<String> SaveNewClient_Commit(ReqNewClientSaveDTO reqNewClientSaveDTO);

    ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO);

    //get user admin profile image
    ResponseEntity<ReqProfileDataAdminsDTO> userAdminGetProfileData(String id, String token);

    //get admin by id
    ResponseEntity<Admin> getAllAdminsSataNySearch(String id , String token);

    ResponseEntity<String> SaveUpdatedAdmin_Prepare(ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO);

    ResponseEntity<String> SaveUpdatedAdmin_Commit(ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO);
}
