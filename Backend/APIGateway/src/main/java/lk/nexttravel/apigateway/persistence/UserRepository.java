package lk.nexttravel.apigateway.persistence;

import lk.nexttravel.apigateway.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:21
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByName(String name);
    boolean existsByName(String username);
    boolean deleteByName(String name);
    ArrayList<User> findAllByNameContains(String keyword);
}
