package org.launchcode.Flickz.models.data;

import org.launchcode.Flickz.models.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by melissa on 5/22/17.
 */
public interface RoleDao extends CrudRepository<Role, Long> {
}
