package com.sacak.utility;




import com.sacak.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager<T extends BaseEntity> implements ICRUD<T> {

     public  ArrayList<T> veriListesi = new ArrayList<>();

    @Override
    public Optional<T> save(T t) {
        if (veriListesi.add(t)) {
            return Optional.ofNullable(t);
        }
        return Optional.empty();
    }

    @Override
    public List<T> saveAll(List<T> t) {
        if (veriListesi.addAll(t)) {
            return t;
        } else {
            return null;
        }
    }

    @Override
    public Optional<T> update(T t) {
        int index = veriListesi.indexOf(t);
        return Optional.ofNullable(veriListesi.set(index, t));
    }

    @Override
    public List<T> findAll() {
        return veriListesi;
    }

    @Override
    public Optional<T> findByID(int id) {
        for (BaseEntity entity : veriListesi ){
            if(entity.getId() == id){
                return Optional.of((T) entity);
            }
        }
        return Optional.empty();
    }

    public int findLastId() {
        return veriListesi.stream()
                .reduce((e1, e2) -> e1.getId() > e2.getId() ? e1 : e2) // Compare and return the element with the higher ID
                .map(BaseEntity::getId) // Extract the ID of the element with the highest ID
                .orElse(0); // Return 0 if the list is empty
    }
}
