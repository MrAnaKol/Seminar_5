package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.MyUser;

public interface IMyUserRepo extends CrudRepository<MyUser, Integer>{

}