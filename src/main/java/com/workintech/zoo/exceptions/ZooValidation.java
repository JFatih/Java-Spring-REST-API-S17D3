package com.workintech.zoo.exceptions;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooValidation {
    private static final String VARIABLE_IS_NULL = "Variable can not be null";

    public static void isIdValid(Integer id){
        if(id == null || id < 0 ){
            throw new ZooException("Id = " + id + " can not less then 0", HttpStatus.BAD_REQUEST);
        }
    }

    public static <T> void isIDExist(int id, Map<Integer, T> datas, boolean existence){
        if(existence){
            if(!datas.containsKey(id)){
                throw new ZooException("Record is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        }else{
            if(datas.containsKey(id)){
                throw new ZooException("Record is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        }

    }

    public static void isVariablesNull(Koala koala){
        if(koala.getName() == null ){
            throw new ZooException("Name can not be null", HttpStatus.BAD_REQUEST);
        }else if( koala.getGender() == null ){
            throw new ZooException("Gender can not be null", HttpStatus.BAD_REQUEST);
        }
    }

    public static void isVariablesNull(Kangaroo kangaroo){
        if(kangaroo.getName() == null ){
            throw new ZooException("Name can not be null", HttpStatus.BAD_REQUEST);
        }else if( kangaroo.getGender() == null ){
            throw new ZooException("Gender can not be null", HttpStatus.BAD_REQUEST);
        }
    }



}
