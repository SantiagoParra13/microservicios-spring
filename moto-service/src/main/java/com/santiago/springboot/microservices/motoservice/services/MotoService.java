package com.santiago.springboot.microservices.motoservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiago.springboot.microservices.motoservice.entities.Moto;
import com.santiago.springboot.microservices.motoservice.repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll() {
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id) {
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto moto) {
        Moto nuevaMoto = motoRepository.save(moto);
        return nuevaMoto;
    }

    public List<Moto> byUsuarioId(int usuarioId) {
        return motoRepository.findByUsuarioId(usuarioId);
    }

}
