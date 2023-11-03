package lk.nexttravel.user_service.api;

import lk.nexttravel.user_service.dto.ReqNewClientSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:05
 */
@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {


    @Autowired
    ClientService clientService;

    //----------Save New Client ------
    @PostMapping(value = "/save_new_client")
    public ResponseEntity<String> SaveNewClient_Prepare(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return clientService.SaveNewClient_Prepare(reqNewClientSaveDTO);
    }
    @PutMapping(value = "/save_new_client")
    public ResponseEntity<String> SaveNewClient_Commit(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return clientService.SaveNewClient_Commit(reqNewClientSaveDTO);
    }
    @DeleteMapping(value = "/save_new_client")
    public ResponseEntity<String> SaveNewClient_Abrot(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return clientService.SaveNewClient_Abrot(reqNewClientSaveDTO);
    }

}
