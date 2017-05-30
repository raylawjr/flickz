package org.launchcode.Flickz.models.data;

import java.util.List;
import org.launchcode.Flickz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by melissa on 5/22/17.
 */

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long>{

    <List>User findByUsername(String username);

    User findById(long id);

}
