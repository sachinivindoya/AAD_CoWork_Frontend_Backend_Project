package lk.nexttravel.user_service.persistence;

import lk.nexttravel.user_service.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:33
 */
@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {
    Optional<Client> findClientById(String id);

}
