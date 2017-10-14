/**
 * 
 */
package in.techshine.service;

/**
 * @author anup
 *
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}