package cars.security.security_jwt;

import cars.dto.constants.CarsApiConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "security.jwt")
public class TokenProperties {

    private String loginPath = CarsApiConstants.LOGIN;

    private String header = "Authorization";

    private String prefix = "Bearer ";

    @Value("${security.jwt.expiration}")
    private int expiration;

    @Value("${security.jwt.secret}")
    private String secret = "JwtSecretKey";

}