package lk.nexttravel.apigateway.service.security;

import lk.nexttravel.apigateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.apigateway.dto.auth.InternalFrontendSecurityCheckDTO;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:54
 */
public interface Authenticate_Authorize_Service {
    InternalFrontendSecurityCheckDTO validateRequestsAndGetMetaData(FrontendTokenDTO frontendTokenDTO);
}
