package com.synowiec.LoginRegister.config;

import com.synowiec.LoginRegister.service.FizjoterapeutaService;
import com.synowiec.LoginRegister.service.PacjentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@EnableWebSecurity
public class SecurityConfiguration{

    @Configuration
    @Order(1)
    public static class PacjentConfigAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        private PacjentService pacjentService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/pacjent/**")
                    .authorizeRequests()
                    .antMatchers(
                            "/registration**",
                            "/FizjoterapeutaLogin**",
                            "/FizjoterapeutaRegister**",
                            "/index**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .antMatchers("/pacjent/**").hasAuthority("Pacjent")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/pacjent/login")
                    .loginProcessingUrl("/pacjent/login")
                    .defaultSuccessUrl("/pacjent/dashboard",true)
                    .permitAll()
                    .and()
//                    .exceptionHandling()
//                    .defaultAuthenticationEntryPointFor(
//                            loginUrlauthenticationEntryPoint2(), new AntPathRequestMatcher("/pacjent/**")
//                   )
//                    .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/pacjent/logout"))
                    .logoutSuccessUrl("/pacjent/login?logout")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationEntryPoint loginUrlauthenticationEntryPoint2(){
            return new LoginUrlAuthenticationEntryPoint("/login");
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(pacjentService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }
    }

    @Configuration
    @Order(2)
    public static class FizjoterapeutaConfigAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        private FizjoterapeutaService fizjoService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/fizjoterapeuta/**")
                    .authorizeRequests()
                    .antMatchers(
                            "/FizjoterapeutaRegister**",
                            "/login**",
                            "/registration**",
                            "/index**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .antMatchers("/fizjoterapeuta/**").hasAuthority("Fizjoterapeuta")
                    .and()
                    .formLogin()
                    .loginPage("/fizjoterapeuta/login")
                    .loginProcessingUrl("/fizjoterapeuta/login")
                    .defaultSuccessUrl("/fizjoterapeuta/dashboard",true)
                    .permitAll()
                    .and()
//                    .exceptionHandling()
//                    .defaultAuthenticationEntryPointFor(
//                            loginUrlauthenticationEntryPoint(), new AntPathRequestMatcher("/fizjoterapeuta/**")
//                    )
//                    .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/fizjoterapeuta/logout"))
                    .logoutSuccessUrl("/fizjoterapeuta/login?logout")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

        @Bean
        public AuthenticationEntryPoint loginUrlauthenticationEntryPoint(){
            return new LoginUrlAuthenticationEntryPoint("/FizjoterapeutaLogin");
        }

        @Bean
        public BCryptPasswordEncoder fizjoPasswordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider fizjoAuthenticationProvider(){
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(fizjoService);
            auth.setPasswordEncoder(fizjoPasswordEncoder());
            return auth;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(fizjoAuthenticationProvider());
        }
    }

}
