package id.co.chubb.fileprocess.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;

@Configuration
public class LdapConfig {
    public static final Logger logger = LoggerFactory.getLogger(LdapConfig.class);
    @Value("${ldap.urls}")
    private String ldapUrl;

    @Value("${ldap.base}")
    private String ldapBase;
    @Value("${ldap.baseDn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapUserServiceAccount;

    @Value("${ldap.password}")
    private String ldapPasswordServiceAccount;

    @Bean
    AuthenticationManager authenticationManager() {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource());
        factory.setUserSearchFilter("(sAMAccountName={0})");
        return factory.createAuthenticationManager();
    }

    @Bean
    public BaseLdapPathContextSource contextSource() {
        LdapContextSource source = new LdapContextSource();
        source.setUrl(ldapUrl);
        source.setUserDn(ldapUserServiceAccount);
        source.setPassword(ldapPasswordServiceAccount);
        source.setBase(ldapBase);
        return source;
    }

    @Bean
    public LdapTemplate ldapTemplate(BaseLdapPathContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
}
