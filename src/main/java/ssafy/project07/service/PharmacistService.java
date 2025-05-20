package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Pharmacist;
import ssafy.project07.dto.pharmacist.PharmacistResponse;
import ssafy.project07.repository.pharmacist.PharmacistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacistService{

    private final PharmacistRepository pharmacistRepository;


    public List<PharmacistResponse> getAllPharmacists() {
        return pharmacistRepository.findAll().stream().map(pharmacist -> {
            PharmacistResponse res = new PharmacistResponse();
            res.setId(pharmacist.getId());
            res.setName(pharmacist.getName());
            res.setProfileImage(pharmacist.getProfileImage());
            return res;
        }).collect(Collectors.toList());



        // 단순 이름 조회
//        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
//        List<String> names = new ArrayList<>();
//        for (Pharmacist pharmacist : pharmacists) {
//            names.add(pharmacist.getName());
//        }
//
//        return names;
    }
}
