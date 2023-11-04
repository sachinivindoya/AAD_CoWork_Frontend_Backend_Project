package lk.nexttravel.guide_service.persistence;

import lk.nexttravel.guide_service.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:43
 */
public interface GuideRepository extends MongoRepository<Guide , String> {
    ArrayList<Guide> findAll();
}
