package lk.nexttravel.apigateway.repostory;

import lk.nexttravel.apigateway.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 16:45
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByName(String name);
    boolean existsByName(String username);
    boolean deleteByName(String name);
    ArrayList<User> findAllByNameContains(String keyword);
}
