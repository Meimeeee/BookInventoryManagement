package BookInventoryManage.example.inventory.Modules.Profile;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
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

    public ProfileEntity getProfileById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile Id must not be empty !!");
        }
        Optional<ProfileEntity> Opt = profileRepository.findById(Id);
        if (Opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Profile by ID: " + Id);
        }
        return Opt.get();
    }
}
