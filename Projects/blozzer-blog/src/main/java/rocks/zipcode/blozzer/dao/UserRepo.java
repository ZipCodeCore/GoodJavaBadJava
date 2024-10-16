package rocks.zipcode.blozzer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.blozzer.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
