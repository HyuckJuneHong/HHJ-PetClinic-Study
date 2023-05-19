package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;

import java.util.List;

public interface ICommonService {

    default <T> void isEmpty(List<T> list, ResponseStatus responseStatus){

        if (list.isEmpty()) {
            throw new NotFoundException(responseStatus);
        }
    }
}
