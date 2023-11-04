package lk.nexttravel.guide_service.api;

import lk.nexttravel.guide_service.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_service.entity.Guide;
import lk.nexttravel.guide_service.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:35
 */


@RestController
@RequestMapping("/guide")
@CrossOrigin("*")
public class GuideController {

    @Autowired
    GuideService guideService;

    //----------Save New Guide ------
    @PostMapping(value = "/save_new_guide")
    public ResponseEntity<String> SaveNewGuide(@RequestBody ReqNewGuideSaveDTO reqNewGuideSaveDTO){
        return guideService.SaveNewGuide(reqNewGuideSaveDTO);
    }

    //----------update Guide ------
    @PutMapping(value = "/save_new_guide")
    public ResponseEntity<String> updateGuidee(@RequestBody ReqNewGuideSaveDTO reqNewGuideSaveDTO){
        return guideService.updateGuide(reqNewGuideSaveDTO);
    }

    //search all guides
    @GetMapping(value = "/getall-guides")
    public ResponseEntity<ArrayList<Guide>> getAllGuides(
            @RequestParam String token
    ){
        return guideService.getAllGuides(token);
    }

    //sdelete guide
    @DeleteMapping(value = "/delete-guide")
    public ResponseEntity<String> deleteGuide(
            @RequestParam String id,
            @RequestParam String token
    ){
        return guideService.deleteGuide(id, token);
    }
}
