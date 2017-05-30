package org.launchcode.Flickz.models.data;

import org.launchcode.Flickz.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by melissa on 5/29/17.
 */

@Repository
@Transactional
public interface ReviewDao extends CrudRepository<Review, Long>{

    Review findById(long id);

    List<Review> findByAuthor(long authorid);
}
