package org.launchcode.Flickz.models.data;

import org.launchcode.Flickz.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by melissa on 5/22/17.
 */
public interface UserDao extends CrudRepository<User, Long>{
}
