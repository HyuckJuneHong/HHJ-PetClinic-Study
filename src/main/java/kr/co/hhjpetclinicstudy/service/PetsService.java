package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsService {
    private final PetsRepository petsRepository;
}
