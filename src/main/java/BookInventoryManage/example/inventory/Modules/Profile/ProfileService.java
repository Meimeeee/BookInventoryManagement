package BookInventoryManage.example.inventory.Modules.Profile;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ProfileRepository;
import BookInventoryManage.example.inventory.Modules.Profile.DTO.UpdateProfileRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public void updateProfile(Integer profileId, UpdateProfileRequestDTO dto) {
        Optional<ProfileEntity> OptProfile = profileRepository.findById(profileId);
        if (OptProfile.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Profile !!");
        else {
            ProfileEntity profile = OptProfile.get();
            profile.setFname(dto.getFname());
            profile.setLname(dto.getLname());
            profile.setDob(dto.getDob());
            profileRepository.save(profile);
        }

    }
}
