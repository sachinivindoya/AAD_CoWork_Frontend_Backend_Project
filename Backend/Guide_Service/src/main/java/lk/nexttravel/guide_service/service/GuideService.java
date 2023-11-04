package lk.nexttravel.guide_service.service;

import lk.nexttravel.guide_service.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_service.entity.Guide;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:45
 */
public interface GuideService {
    ResponseEntity<String> SaveNewGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<ArrayList<Guide>> getAllGuides(String token);

    ResponseEntity<String> updateGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<String> deleteGuide(String id, String token);
}
