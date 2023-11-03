package lk.nexttravel.user_service.persistence;

import lk.nexttravel.user_service.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:31
 */
@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {
    Optional<Admin> findAdminById(String id);

}
