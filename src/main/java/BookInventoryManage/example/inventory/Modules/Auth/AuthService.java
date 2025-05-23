package BookInventoryManage.example.inventory.Modules.Auth;

import BookInventoryManage.example.inventory.Modules.Auth.DTO.BasicLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.TokenResponseDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import BookInventoryManage.example.inventory.Utils.Configs.BookInventoryConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.Configuration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    AccountReposity accountReposity;

    @Autowired
    BookInventoryConfiguration configuration;

    public TokenResponseDTO basicLogin(BasicLoginDTO dto) {
        Optional<AccountEntity> result = accountReposity.findByEmail(dto.getEmail());
        if (result.isEmpty()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username !!");
        AccountEntity acc = result.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(dto.getPassword(), acc.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password !!");
        }

        return new TokenResponseDTO(signAccessToken(acc));
    }

    //    encode
    public String signAccessToken(AccountEntity account) {
        Instant now = Instant.now();
        Algorithm algorithm = Algorithm.HMAC256(configuration.getJwtSecret());
        return JWT.create()
                .withSubject(account.getId().toString())
                .withIssuer(configuration.getJwtIssuer())
                .withIssuedAt(now)
                .withExpiresAt(now.plus(1, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    //    decode
    public Optional<AccountEntity> verifyAccessToken(String accessToken) {
        Algorithm algorithm = Algorithm.HMAC256(configuration.getJwtSecret());
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(configuration.getJwtIssuer())
                .build()
                .verify(accessToken);
        Integer accountId = Integer.valueOf(decodedJWT.getSubject());
        return accountReposity.findById(accountId);
    }

}
