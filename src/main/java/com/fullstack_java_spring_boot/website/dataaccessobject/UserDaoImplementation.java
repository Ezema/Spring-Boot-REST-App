package com.fullstack_java_spring_boot.website.dataaccessobject;

import com.fullstack_java_spring_boot.website.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImplementation implements UserDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> getUsers(){
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        System.out.print("result from DB selection to delete is");
        System.out.print(user);
        entityManager.remove(user);
    }

    @Override
    public void registerUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User ifExitsReturnUserObject(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> userList = entityManager.createQuery(query).setParameter("email", user.getEmail()).getResultList();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (userList.isEmpty()){
            System.out.print("USER list is empty");
            return null;
        }
        if(argon2.verify(userList.get(0).getPassword(),user.getPassword())){
            return userList.get(0);
        }else{
            System.out.print("could not verify");
            return null;
        }
    }

}
