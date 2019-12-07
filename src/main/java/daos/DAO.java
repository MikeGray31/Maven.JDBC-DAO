package daos;

import java.util.List;

public interface DAO <T>{
    public T findById(Integer id);
    public List<T> findAll();
    public T update(T dto);
    public T create(T dto);
    public void delete(Integer id);

}
