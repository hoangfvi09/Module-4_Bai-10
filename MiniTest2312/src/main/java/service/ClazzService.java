package service;

import model.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.IClazzRepository;

import java.util.Optional;
@Service
public class ClazzService implements IClazzService {

    @Autowired
    private IClazzRepository clazzRepository;


    @Override
    public Iterable<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return clazzRepository.findById(id);
    }


}
