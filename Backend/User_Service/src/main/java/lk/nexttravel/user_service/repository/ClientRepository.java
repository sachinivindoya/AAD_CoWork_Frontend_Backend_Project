package lk.nexttravel.user_service.repository;

import lk.nexttravel.user_service.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 18:01
 */
public interface ClientRepository extends MongoRepository<Client,Long> {
    Optional<Client> findClientById(String id);
}
