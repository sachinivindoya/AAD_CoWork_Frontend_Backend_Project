package lk.nexttravel.user_service.service;

import lk.nexttravel.user_service.dto.ReqNewClientSaveDTO;
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

}
