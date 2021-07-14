package com.adi.shop.config;

import com.adi.shop.model.User;
import com.adi.shop.model.item.Item;
import com.adi.shop.repository.ItemRepository;
import com.adi.shop.repository.UserRepository;
import com.adi.shop.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository, ItemRepository itemRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll();
                .antMatchers(HttpMethod.GET,"/api","/api/all").permitAll()
                .antMatchers(HttpMethod.POST,"/api").hasAnyRole("USER","MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/api").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

    }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // method to add users on the start of the application -> testing purposes
        @EventListener(ApplicationReadyEvent.class)
        public void get() {
            User user = new User("user", passwordEncoder().encode("user"), "USER");
            userRepository.save(user);

            User mod = new User("mod", passwordEncoder().encode("mod"), "MODERATOR");
            userRepository.save(mod);

            User admin = new User("admin", passwordEncoder().encode("admin"), "ADMIN");
            userRepository.save(admin);

            Item fidgetSpinner = new Item();
            fidgetSpinner.setName("Fidget spinner");
            fidgetSpinner.setDescription("Small toy that spins in your hand");
            fidgetSpinner.setPrice(6);
            itemRepository.save(fidgetSpinner);

            Item hotWheelsCar = new Item();
            hotWheelsCar.setName("HotWheels car");
            hotWheelsCar.setDescription("HotWheels car that can be driven on HotWheels tracks");
            hotWheelsCar.setPrice(25);
            itemRepository.save(hotWheelsCar);

            Item hotWheelsTrack = new Item();
            hotWheelsTrack.setName("HotWheels track");
            hotWheelsTrack.setDescription("Special track for HotWheels cars with two loops");
            hotWheelsTrack.setPrice(15);
            itemRepository.save(hotWheelsTrack);
        }

    }

