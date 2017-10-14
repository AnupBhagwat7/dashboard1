/**
 * 
 */
package in.techshine.service;

import in.techshine.beans.User;

/**
 * @author anup
 *
 */

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}