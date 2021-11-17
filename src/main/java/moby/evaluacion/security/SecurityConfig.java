package moby.evaluacion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static moby.evaluacion.utils.Constants.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/candidato/cargar-candidato/").hasRole(ROL_ADMIN)
                .antMatchers("/candidato/eliminar-candidato/").hasRole(ROL_ADMIN)
                .antMatchers("/candidato/modificar-candidato/").hasRole(ROL_ADMIN)
                .antMatchers("/candidato/mostrar-candidato/").hasAnyRole(ROL_USER, ROL_ADMIN)
                .antMatchers("/tecnologia/cargar-tecnologia/").hasRole(ROL_ADMIN)
                .antMatchers("/tecnologia/eliminar-tecnologia/").hasRole(ROL_ADMIN)
                .antMatchers("/tecnologia/modificar-tecnologia/").hasRole(ROL_ADMIN)
                .antMatchers("/tecnologia/mostrar-tecnologia/").hasAnyRole(ROL_USER,ROL_ADMIN)
                .antMatchers("/exp/cargar-tecnologia/").hasAnyRole( ROL_USER,ROL_ADMIN)
                .antMatchers("/exp/candidatos-tecnologia/").hasAnyRole(ROL_ADMIN)
                .and().formLogin();
    }
}