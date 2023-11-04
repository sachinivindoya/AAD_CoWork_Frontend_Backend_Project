package lk.nexttravel.apigateway;

import jakarta.annotation.PostConstruct;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ApiGatewayApplication {

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String UserServiceMngrImg ;
    private String HotelServiceMngrImg ;
    private String TravelPackageServiceMngrImg ;
    private String VehicleServiceMngrImg;
    private String GuideServiceMngrImg;


    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() throws IOException {
        return args -> {

            if(!userRepository.existsByName("kaligu")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("kaligu")
                                .signup_name_with_initial("H.C.K.Jayanath Perera")
                                .signup_address("Horana")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("200133702832")
                                .signup_password("Kaligu@1234")
                                .signup_profile_image(UserServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_USER
                );
            }
            if(!userRepository.existsByName("nimal")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("nimal")
                                .signup_name_with_initial("P.Nimal Perera")
                                .signup_address("Colombo")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199899858533")
                                .signup_password("Nimal@1234")
                                .signup_profile_image(VehicleServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE
                );
            }
            if(!userRepository.existsByName("sanura")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("sanura")
                                .signup_name_with_initial("K.P.M.Sanura Perera")
                                .signup_address("Padukka")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199658652548")
                                .signup_password("Sanura@1234")
                                .signup_profile_image(TravelPackageServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE
                );
            }
            if(!userRepository.existsByName("pasan")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("pasan")
                                .signup_name_with_initial("P.K.K.Pasan Athapaththu")
                                .signup_address("Ingiriya")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199665859354")
                                .signup_password("Pasan@1234")
                                .signup_profile_image(HotelServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_HOTEL
                );
            }
            if(!userRepository.existsByName("nirot")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("namindu")
                                .signup_name_with_initial("R.M.Namindu Hasaranga")
                                .signup_address("Kalutara")
                                .signup_email("sachinivonodya2001@gmail.com")
                                .signup_nic_or_passport("200167202516")
                                .signup_password("Namidu@123")
                                .signup_profile_image(GuideServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_GUIDE
                );
            }


        };


    }


    @PostConstruct
    public void initializeImages() {
        try {
            UserServiceMngrImg = Files.readString(Paths.get("D:\\Final_AAD\\Backend\\APIGateway\\src\\main\\resources\\UserServiceMngrImg.txt"));
            HotelServiceMngrImg = Files.readString(Paths.get("D:\\Final_AAD\\Backend\\APIGateway\\src\\main\\resources\\HotelServiceMngrImg.txt"));
            GuideServiceMngrImg = Files.readString(Paths.get("D:\\Final_AAD\\Backend\\APIGateway\\src\\main\\resources\\GuideServiceMngrImg.txt"));
            VehicleServiceMngrImg = Files.readString(Paths.get("D:\\Final_AAD\\Backend\\APIGateway\\src\\main\\resources\\VehicleServiceMngrImg.txt"));
            TravelPackageServiceMngrImg = Files.readString(Paths.get("D:\\Final_AAD\\Backend\\APIGateway\\src\\main\\resources\\TravelPackageServiceMngrImg.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
