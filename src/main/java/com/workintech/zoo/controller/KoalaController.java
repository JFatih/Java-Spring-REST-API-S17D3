package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        this.koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getKoala(){
        return koalas.values().stream().toList();
    }

    @GetMapping("{id}")
    public Koala getKoala(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id, koalas, true);
        return koalas.get(id);
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala){
        ZooValidation.isIdValid(koala.getId());
        ZooValidation.isVariablesNull(koala);
        ZooValidation.isIDExist(koala.getId(),koalas,false);

        Koala newKoala = new Koala(koala.getId(), koala.getName(), koala.getWeight(), koala.getSleepHour(),koala.getGender());
        koalas.put(newKoala.getId(),newKoala);
        return newKoala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id, @RequestBody Koala koala){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id,koalas,true);
        ZooValidation.isVariablesNull(koala);
        koalas.remove(id);
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id,koalas,true);
        Koala deletedKoala = koalas.get(id);
        koalas.remove(id);
        return deletedKoala;
    }
}
