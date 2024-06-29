package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import com.workintech.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    Map<Integer, Kangaroo> kangaroos;


    @PostConstruct
    public void init(){
        this.kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("{id}")
    public Kangaroo getKangaroo(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id, kangaroos, true);
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){
        ZooValidation.isIdValid(kangaroo.getId());
        ZooValidation.isVariablesNull(kangaroo);
        ZooValidation.isIDExist(kangaroo.getId(),kangaroos,false);

        Kangaroo newKangaroo = new Kangaroo(kangaroo.getId(), kangaroo.getName(), kangaroo.getHeight(), kangaroo.getWeight(), kangaroo.getGender(), kangaroo.getIsAggressive());
        kangaroos.put(newKangaroo.getId(),newKangaroo);
        return newKangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id,kangaroos,true);
        ZooValidation.isVariablesNull(kangaroo);
        kangaroos.remove(id);
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.isIDExist(id,kangaroos,true);
        Kangaroo deletedKangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return deletedKangaroo;
    }
}
