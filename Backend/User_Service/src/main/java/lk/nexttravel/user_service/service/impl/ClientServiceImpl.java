package lk.nexttravel.user_service.service.impl;

import lk.nexttravel.user_service.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_service.persistence.ClientRepository;
import lk.nexttravel.user_service.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:53
 */
public class ClientServiceImpl implements ClientService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> SaveNewClient_Prepare(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveNewClient_Commit(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        return null;
    }
}
