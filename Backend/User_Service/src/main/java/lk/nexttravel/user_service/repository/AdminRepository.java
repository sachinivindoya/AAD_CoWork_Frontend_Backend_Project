package lk.nexttravel.user_service.repository;

import lk.nexttravel.user_service.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 18:00
 */
public interface AdminRepository extends MongoRepository<Admin,Long> {
    Optional<Admin> findAdminById(String id);
}
