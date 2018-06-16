package br.com.spedroza.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.spedroza.bookstore.model.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Inside UserDAO.getUser...");
		System.out.println("Querying for user : " + email);
		List<User> user = manager.createQuery("select u from User u where u.email = :email", User.class)
				.setParameter("email", email).getResultList();
		// check user exists
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found for email " + email);
		}
		return user.get(0);
	}
}
