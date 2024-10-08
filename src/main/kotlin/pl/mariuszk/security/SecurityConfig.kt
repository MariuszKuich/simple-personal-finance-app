package pl.mariuszk.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/api/public/**").permitAll()
                    .anyRequest().authenticated() }
            .securityContext { it.securityContextRepository(securityContextRepository()) }
            .logout { it.logoutUrl("/api/public/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
                .permitAll() }
            .build()
    }

    @Bean
    fun securityContextRepository(): SecurityContextRepository = HttpSessionSecurityContextRepository()

    @Bean
    fun authenticationManager(http: HttpSecurity, userDetailsService: UserDetailsService): AuthenticationManager {
        val authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
        return authManagerBuilder.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}