package com.sencerseven.basittarifler.config;

import com.sencerseven.basittarifler.repository.UserRepository;
import com.sencerseven.basittarifler.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class WebSecurityConfig {


    @Configuration
    @Order(1)
    public static class AdminSecurity extends WebSecurityConfigurerAdapter{

        @Autowired
        CustomUserDetailsService userDetailsService;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
        }

        @Bean(name = "passwordEncoder")
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        private PasswordEncoder getPasswordEncoder() {
            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return charSequence.equals(s);
                }
            };
        }

        @Bean
        public SessionRegistry sessionRegistry() {
            return new SessionRegistryImpl();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/admin/**").authorizeRequests().anyRequest()
                    .hasRole("ADMIN")
                    .and()
                    .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                    .and()
                    .logout().permitAll();


            http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

            http.csrf().disable();

            http.headers().frameOptions().disable();

        }


    }

    @Configuration
    public static class HomeSecurity extends WebSecurityConfigurerAdapter{

        @Autowired
        CustomUserDetailsService userDetailsService;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
        }

        @Bean(name = "passwordEncoder")
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        private PasswordEncoder getPasswordEncoder() {
            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return charSequence.equals(s);
                }
            };
        }

        @Bean
        public SessionRegistry sessionRegistry() {
            return new SessionRegistryImpl();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .antMatchers("/recipe/add").hasAnyRole("ADMIN","USER")
                    .antMatchers("/h2-console","/h2-console/**","/login").permitAll()
                    .and()
                    .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                    .and()
                    .logout().permitAll();



            http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

            http.csrf().disable();

            http.headers().frameOptions().disable();

        }


    }
}
