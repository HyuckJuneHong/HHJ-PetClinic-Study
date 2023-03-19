package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.repository.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;
}
