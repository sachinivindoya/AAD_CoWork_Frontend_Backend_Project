package lk.nexttravel.apigateway.api;

import lk.nexttravel.apigateway.advice.util.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:45
 */
@RestController
@RequestMapping("/guide-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class GuideServiceController {
    @Autowired
    GuideService guideService;

    //user login
    @GetMapping(value = {"/guide-admin-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileDataa(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return guideService.UserAdminGetProfileData(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

    //update Profile Data - guide admin
    @PostMapping(value = {"/guide-admin-update-profile-data"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> userAdminUpdateProfileData(
            @RequestPart("id") String id,
            @RequestPart("username") String username,
            @RequestPart("address") String address,
            @RequestPart("email") String email,
            @RequestPart("nic") String nic,
            @RequestPart("password") String password,
            @RequestPart("nameinitial") String nameinitial,
            @RequestPart("profileImage_Base64String") String profileImage_Base64String,
            @RequestPart("access_username") String access_username,
            @RequestPart("access_jwt_token") String access_jwt_token,
            @RequestPart("access_refresh_token") String access_refresh_token
    ){
        if(
                username.matches(RegaxStrings.NameRegax) &&
                        address.matches(RegaxStrings.AddressRegax) &&
                        email.matches(RegaxStrings.EmailRegax) &&
                        nic.matches(RegaxStrings.NICRegax) &&
                        password.matches(RegaxStrings.PasswordRegax) &&
                        nameinitial.matches(RegaxStrings.NameWithInitialRegax)


        ){

            return guideService.UserAdminUpdateProfileData(
                    id,
                    username,
                    address,
                    email,
                    nic,
                    password,
                    nameinitial,
                    profileImage_Base64String,
                    access_username,
                    access_jwt_token,
                    access_refresh_token
            );
        }else {
            System.out.println("error");
            return Mono.error( new InvalidInputException("Data Invalid!") );
        }
    }

    //Add new Guide
    @PostMapping(value = {"/create-new-guide"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> createNewGuide(
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("remarks") String remarks,
            @RequestPart("experience") String experience,
            @RequestPart("nic") String nic,
            @RequestPart("nic_front_view") String nicFrontView,
            @RequestPart("nic_rear_view") String nicRearView,
            @RequestPart("tell") String tell,
            @RequestPart("gender") String gender,
            @RequestPart("dob") String dob,
            @RequestPart("image") String image,
            @RequestPart("perday_fee") String perdayFee,
            @RequestPart("access_username") String accessUsername,
            @RequestPart("access_jwt_token") String accessToken,
            @RequestPart("access_refresh_token") String refreshToken
    ){
        if(
                name.matches(RegaxStrings.NameRegax) &&
                        address.matches(RegaxStrings.AddressRegax)  &&
                        remarks.matches(RegaxStrings.TextRegax)  &&
                        experience.matches(RegaxStrings.OnlyNumberRegax)  &&
                        nic.matches(RegaxStrings.NICRegax)  &&
                        tell.matches(RegaxStrings.TellRegax)  &&
                        gender.matches(RegaxStrings.GenderRegax)  &&
                        dob.matches(RegaxStrings.DOBRegax)  &&
                        perdayFee.matches(RegaxStrings.AddressRegax)
        ){

            return guideService.createNewGuide(
                    name,
                    address,
                    remarks,
                    experience,
                    nic,
                    nicFrontView,
                    nicRearView,
                    tell,
                    gender,
                    dob,
                    image,
                    perdayFee,
                    accessUsername,
                    accessToken,
                    refreshToken
            );

        }else {
            System.out.println("error");
            return Mono.error( new InvalidInputException("Data Invalid!") );
        }
    }

    //update Guide
    @PutMapping(value = {"/update-guide"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> updateGuide(
            @RequestPart("id") String id,
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("remarks") String remarks,
            @RequestPart("experience") String experience,
            @RequestPart("nic") String nic,
            @RequestPart("nic_front_view") String nicFrontView,
            @RequestPart("nic_rear_view") String nicRearView,
            @RequestPart("tell") String tell,
            @RequestPart("gender") String gender,
            @RequestPart("dob") String dob,
            @RequestPart("image") String image,
            @RequestPart("perday_fee") String perdayFee,
            @RequestPart("access_username") String accessUsername,
            @RequestPart("access_jwt_token") String accessToken,
            @RequestPart("access_refresh_token") String refreshToken
    ){
        if(
                name.matches(RegaxStrings.NameRegax) &&
                        address.matches(RegaxStrings.AddressRegax)  &&
                        remarks.matches(RegaxStrings.TextRegax)  &&
                        experience.matches(RegaxStrings.OnlyNumberRegax)  &&
                        nic.matches(RegaxStrings.NICRegax)  &&
                        tell.matches(RegaxStrings.TellRegax)  &&
                        gender.matches(RegaxStrings.GenderRegax)  &&
                        dob.matches(RegaxStrings.DOBRegax)  &&
                        perdayFee.matches(RegaxStrings.AddressRegax)
        ){

            return guideService.updateGuide(
                    id,
                    name,
                    address,
                    remarks,
                    experience,
                    nic,
                    nicFrontView,
                    nicRearView,
                    tell,
                    gender,
                    dob,
                    image,
                    perdayFee,
                    accessUsername,
                    accessToken,
                    refreshToken
            );

        }else {
            return Mono.error( new InvalidInputException("Data Invalid!") );
        }
    }

    //admin manage window- search all admins
    @GetMapping(value = {"/guides-getall"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> getAllAdminsDataList(
            @RequestParam("access_username") @NonNull String access_username,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return guideService.getAllGuides(
                access_username,
                access_jwt_token,
                access_refresh_token
        );
    }

    //delete guide
    @GetMapping(value = {"/delete-guide"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> deleteGuide(
            @RequestParam("id")  String id
            ,

            @RequestParam("access_username")  String access_username
            ,
            @RequestParam("access_jwt_token") String access_jwt_token
            ,
            @RequestParam("access_refresh_token")  String access_refresh_token
    ){
        System.out.println("requested");
        return guideService.deleteGuide(
                id,

                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }
}
