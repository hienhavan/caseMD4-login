package org.example.caselogin.service.appUser;


import org.example.caselogin.model.User;
import org.example.caselogin.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<User>, UserDetailsService {
}
