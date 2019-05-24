package vn.cdw.cdwforums.reponsitory;

import org.springframework.data.repository.CrudRepository;

import vn.cdw.cdwforums.entity.Photo;


public interface PhotoRepository  extends CrudRepository<Photo, Long> {
    Photo findByUserId(Long id);
}
