package org.launchcode.Flickz.models.data;

import org.launchcode.Flickz.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by melissa on 5/22/17.
 */

@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Long> {
}
