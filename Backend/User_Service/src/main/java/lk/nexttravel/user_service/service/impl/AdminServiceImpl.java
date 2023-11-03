package lk.nexttravel.user_service.service.impl;

import lk.nexttravel.user_service.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_service.dto.ReqProfileDataAdminsDTO;
import lk.nexttravel.user_service.dto.ReqUpdateGuideAdminDTO;
import lk.nexttravel.user_service.entity.Admin;
import lk.nexttravel.user_service.persistence.AdminRepository;
import lk.nexttravel.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:41
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> SaveNewClient_Prepare(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token

                System.out.println(reqNewClientSaveDTO.getName_with_initial());
                //save into database
                Admin admin = adminRepository.save(
                        Admin.builder()
                                .id(reqNewClientSaveDTO.getId())
                                .address(reqNewClientSaveDTO.getAddress())
                                .profile_image(reqNewClientSaveDTO.getProfile_image())
                                .signup_name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                .transaction_state(RespondCodes.PENDING)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

    @Override
    public ResponseEntity<String> SaveNewClient_Commit(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token
                //save into database
                adminRepository.save(
                        Admin.builder()
                                .id(reqNewClientSaveDTO.getId())
                                .address(reqNewClientSaveDTO.getAddress())
                                .profile_image(reqNewClientSaveDTO.getProfile_image())
                                .signup_name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                .transaction_state(RespondCodes.COMMITED)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ReqProfileDataAdminsDTO> userAdminGetProfileData(String id, String token) {
        return null;
    }

    @Override
    public ResponseEntity<Admin> getAllAdminsSataNySearch(String id, String token) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveUpdatedAdmin_Prepare(ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveUpdatedAdmin_Commit(ReqUpdateGuideAdminDTO reqUpdateGuideAdminDTO) {
        return null;
    }
}
