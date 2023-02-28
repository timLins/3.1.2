package ru.alishev.springcourse.project2boot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.project2boot.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
