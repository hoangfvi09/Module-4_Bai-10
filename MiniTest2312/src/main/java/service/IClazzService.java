package service;

import model.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClazzService <Clazz>{
    Iterable<Clazz> findAll();
    Optional findById(Long id);

}
