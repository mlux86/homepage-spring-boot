package de.l0x.homepage.web;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // Allow cache eviction only from localhost.
        http.authorizeRequests()
                .antMatchers("/evict").hasIpAddress("127.0.0.1")
                .antMatchers("/evict").hasIpAddress("::1")
                .antMatchers("/**").permitAll();
    }

}
