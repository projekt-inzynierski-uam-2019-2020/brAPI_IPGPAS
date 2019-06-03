package org.brapimanagementservice.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                httpBasic().
                and().
                authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/servers").permitAll().
                and().
                authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/servers/*").permitAll().
                and().
                authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/organisations").permitAll().
                and().
                authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/organisations/*").permitAll().
                and().
                authorizeRequests().anyRequest().authenticated();
    }
}
