package lk.nexttravel.guide_service.service.impl;

import lk.nexttravel.guide_service.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_service.entity.Guide;
import lk.nexttravel.guide_service.persistence.GuideRepository;
import lk.nexttravel.guide_service.service.GuideService;
import lk.nexttravel.guide_service.service.SequenceGeneratorService;
import lk.nexttravel.guide_service.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.guide_service.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:46
 */
@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public ResponseEntity<String> SaveNewGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        //check authentication
        System.out.println(reqNewGuideSaveDTO.getName());
        String id = "G00"+sequenceGeneratorService.generateSequence(Guide.SEQUENCE_NAME);
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //save into database
                guideRepository.save(
                        Guide.builder()
                                .id(id)
                                .name(reqNewGuideSaveDTO.getName())
                                .remarks(reqNewGuideSaveDTO.getRemarks())
                                .experience(reqNewGuideSaveDTO.getExperience())
                                .nic(reqNewGuideSaveDTO.getNic())
                                .nic_front_view(reqNewGuideSaveDTO.getNic_front_view())
                                .nic_rear_view(reqNewGuideSaveDTO.getNic_rear_view())
                                .tell(reqNewGuideSaveDTO.getTell())
                                .gender(reqNewGuideSaveDTO.getGender())
                                .dob(reqNewGuideSaveDTO.getDob())
                                .image(reqNewGuideSaveDTO.getImage())
                                .address(reqNewGuideSaveDTO.getAddress())
                                .perday_fee(reqNewGuideSaveDTO.getPerday_fee())
                                .transaction_state(RespondCodes.COMMITED)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ArrayList<Guide>> getAllGuides(String token) {
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(token)) {  //check gateway token

                ArrayList<Guide> guideArrayList= guideRepository.findAll();

                return new ResponseEntity<>(guideArrayList, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //save into database
                guideRepository.save(
                        Guide.builder()
                                .id(reqNewGuideSaveDTO.getId())
                                .name(reqNewGuideSaveDTO.getName())
                                .remarks(reqNewGuideSaveDTO.getRemarks())
                                .experience(reqNewGuideSaveDTO.getExperience())
                                .nic(reqNewGuideSaveDTO.getNic())
                                .nic_front_view(reqNewGuideSaveDTO.getNic_front_view())
                                .nic_rear_view(reqNewGuideSaveDTO.getNic_rear_view())
                                .tell(reqNewGuideSaveDTO.getTell())
                                .gender(reqNewGuideSaveDTO.getGender())
                                .dob(reqNewGuideSaveDTO.getDob())
                                .image(reqNewGuideSaveDTO.getImage())
                                .address(reqNewGuideSaveDTO.getAddress())
                                .perday_fee(reqNewGuideSaveDTO.getPerday_fee())
                                .transaction_state(RespondCodes.COMMITED)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteGuide(String id, String token) {
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(token)) {  //check gateway token

                guideRepository.delete(guideRepository.findById(id).get());

                return new ResponseEntity<>("done", HttpStatus.OK);

            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
