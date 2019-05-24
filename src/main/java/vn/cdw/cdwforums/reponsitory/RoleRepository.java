package vn.cdw.cdwforums.reponsitory;

import org.springframework.data.repository.CrudRepository;

import vn.cdw.cdwforums.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByAuthority(String authority);

}
