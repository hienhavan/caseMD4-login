////package org.example.caselogin.config;
////
////import org.example.caselogin.controller.CustomSuccessHandle;
////import org.example.caselogin.model.ROLE;
////import org.example.caselogin.service.appUser.AppUserService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private AppUserService appUserService;
////
////    @Autowired
////    private CustomSuccessHandle customSuccessHandle;
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(appUserService);
//////        authenticationProvider.setPasswordEncoder(passwordEncoder());
////        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
////
////        return authenticationProvider;
////    }
////
////        @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
////                        .requestMatchers("/login", "/register").permitAll()
////                        .requestMatchers("/admin/**").hasAuthority(ROLE.ROLE_ADMIN.getRoleName())
////                        .requestMatchers("/lecturer/**").hasAuthority(ROLE.ROLE_LECTURER.getRoleName())
////                        .requestMatchers("/student/**").hasAuthority(ROLE.ROLE_STUDENT.getRoleName())
////                        .requestMatchers("/staff/**").hasAuthority(ROLE.ROLE_STAFF.getRoleName())
////                        .anyRequest().authenticated()
////                )
////                .formLogin(formLogin -> formLogin
////                        .loginPage("/login")
////                        .successHandler(customSuccessHandle)
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .permitAll()
////                );
////
////        return http.build();
////    }
//////    @Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http.formLogin(Customizer.withDefaults()).authorizeHttpRequests(
//////                authorizeRequests -> authorizeRequests.anyRequest().authenticated()
//////        );
//////        return http.build();
//////    }
////}
//
//package org.example.caselogin.config;
//
//import org.example.caselogin.controller.CustomSuccessHandle;
//import org.example.caselogin.service.appUser.AppUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private AppUserService appUserService;
//
//    @Autowired
//    private CustomSuccessHandle customSuccessHandler;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Sử dụng BCryptPasswordEncoder để mã hóa mật khẩu
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(appUserService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/login", "/register").permitAll() // Cho phép truy cập không yêu cầu xác thực
//                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // Quyền truy cập cho admin
//                        .requestMatchers("/lecturer/**").hasAuthority("ROLE_LECTURER") // Quyền truy cập cho lecturer
//                        .requestMatchers("/student/**").hasAuthority("ROLE_STUDENT") // Quyền truy cập cho student
//                        .requestMatchers("/staff/**").hasAuthority("ROLE_STAFF") // Quyền truy cập cho staff
//                        .anyRequest().authenticated() // Các yêu cầu khác đều yêu cầu xác thực
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login") // Đường dẫn trang đăng nhập
//                        .successHandler(customSuccessHandler) // Xử lý thành công đăng nhập
//                        .permitAll() // Cho phép tất cả người dùng truy cập trang đăng nhập
//                )
//                .logout(logout -> logout
//                        .permitAll() // Cho phép tất cả người dùng đăng xuất
//                );
//        return http.build();
//    }
//}
package org.example.caselogin.config;

import org.example.caselogin.controller.CustomSuccessHandle;
import org.example.caselogin.model.ROLE;
import org.example.caselogin.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AppUserService appUserService;

//    @Autowired
//    private CustomSuccessHandle customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authenticationProvider;
    }

    @Bean
    public CustomSuccessHandle customSuccessHandle() {
        return new CustomSuccessHandle();
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/error_404", "/css/**", "/js/**", "/images/**", "/dangky").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/dangky").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .permitAll()
//                )
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                    .requestMatchers("/login", "/register").permitAll()
                                .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()

                                .requestMatchers("/admin/**").hasAuthority(ROLE.ROLE_ADMIN.getRoleName())
                                .requestMatchers("/lecturer/**").hasAuthority(ROLE.ROLE_LECTURER.getRoleName())
                                .requestMatchers("/student/**").hasAuthority(ROLE.ROLE_STUDENT.getRoleName())
                                .requestMatchers("/staff/**").hasAuthority(ROLE.ROLE_STAFF.getRoleName())
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
//                    .loginPage("/login")
                                .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }


}
