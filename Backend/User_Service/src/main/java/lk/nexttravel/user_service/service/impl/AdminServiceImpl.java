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

import java.util.Optional;

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
        public ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO){
            //check authentication
            try {
                if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token
                    //delete
                    adminRepository.delete(
                            Admin.builder()
                                    .id(reqNewClientSaveDTO.getId())
                                    .address(reqNewClientSaveDTO.getAddress())
                                    .profile_image(reqNewClientSaveDTO.getProfile_image())
                                    .signup_name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                    .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                    .build()
                    );

                    return new ResponseEntity<>(RespondCodes.Respond_DATA_DELETED, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public ResponseEntity<ReqProfileDataAdminsDTO> userAdminGetProfileData(String id, String token) {
        try {
            System.out.println("done");
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(token)) {  //check gateway token
                //get image string
                Optional<Admin> admin = adminRepository.findAdminById(id);
                if(admin.isPresent()){

                    return  new ResponseEntity<> (
                            ReqProfileDataAdminsDTO.builder()
                                    .name_with_initial(admin.get().getSignup_name_with_initial())
                                    .nic_or_passport(admin.get().getNic_or_passport())
                                    .address(admin.get().getAddress())
                                    .profile_image(admin.get().getProfile_image())
                                    .build()
                            , HttpStatus.OK);

                }else{
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }else{
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
