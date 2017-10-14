/**
 * 
 */
package in.techshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.techshine.beans.Role;

/**
 * @author anup
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
}
