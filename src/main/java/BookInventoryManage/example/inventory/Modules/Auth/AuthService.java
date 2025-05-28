package BookInventoryManage.example.inventory.Modules.Auth;

import BookInventoryManage.example.inventory.Modules.Account.AccountService;
import BookInventoryManage.example.inventory.Modules.Account.DTO.UpdateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.BasicLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.GoogleLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.TokenResponseDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import BookInventoryManage.example.inventory.Modules.Profile.DTO.UpdateProfileRequestDTO;
import BookInventoryManage.example.inventory.Modules.Profile.ProfileService;
import BookInventoryManage.example.inventory.Utils.Configs.BIConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {
    //   đổi repo thành service
    @Autowired
    AccountReposity accountReposity;

    @Autowired
    AccountService accountService;

    @Autowired
    BIConfiguration configuration;

    @Autowired
    ProfileService profileService;


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

    //    get profile
    public AccountEntity getProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        return (AccountEntity) context.getAuthentication().getPrincipal();
    }

    //    update profile
    public void updateProfile(UpdateProfileRequestDTO dto) {
        SecurityContext context = SecurityContextHolder.getContext();
        AccountEntity acc = (AccountEntity) context.getAuthentication();
        profileService.updateProfile(acc.getProfile().getId(), dto);
    }

    //        update pass
    public void updateAccount(UpdateAccountRequestDTO dto) {
        SecurityContext context = SecurityContextHolder.getContext();
        AccountEntity account = (AccountEntity) context.getAuthentication().getPrincipal();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getOldPassword(), account.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password !!");
        } else {
            account.setPassword(encoder.encode(dto.getNewPassword()));
            accountReposity.save(account);
        }
    }

    //    login Google
    public String googleLogin(GoogleLoginDTO dto) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(configuration.getGoogleClientId()))
                .build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(dto.getCredential());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            Optional<AccountEntity> result = accountReposity.findByEmail(email);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not register yet !!");
            }
            AccountEntity account = result.get();
            return signAccessToken(account);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ID Token is null !!");
        }
    }

}
