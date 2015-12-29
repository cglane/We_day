package com.theironyard.Services;
import com.theironyard.Entities.Invite;
import com.theironyard.Entities.Wedding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by macbookair on 12/8/15.
 */
public interface WeddingRepository extends CrudRepository<Wedding,Integer>{
    List <Wedding> findById(Integer id);
}
