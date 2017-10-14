/**
 * 
 */
package in.techshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.techshine.beans.User;

/**
 * @author anup
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
