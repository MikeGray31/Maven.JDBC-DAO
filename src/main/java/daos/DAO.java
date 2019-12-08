package daos;

import dtos.Person;

import java.util.List;

public interface DAO <T>{
    public T findById(Integer id);
    public List<T> findAll();
    public T update(Person dto);
    public T create(Person dto);
    public void delete(Integer id);

}
