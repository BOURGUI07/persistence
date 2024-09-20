package com.example.CH04.repo;

import com.example.CH04.domain.User;
import com.example.CH04.model.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByUsernameContaining(String text);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> findByUsernameOrEmail(String username, String email);
    List<User> findByLevelOrderByUsernameDesc(int level);
    List<User> findByLevelGreaterThanEqual(int level);
    List<User> findByUsernameStartingWith(String start);
    List<User> findByUsernameEndingWith(String end);
    List<User> findByActive(boolean active);
    Optional<User> findFirstByOrderByUsernameAsc();
    Optional<User> findTopByOrderByRegistrationDateDesc();
    @Override
    Page<User> findAll(Pageable pageable);
    List<User> findByLevel(int level, Sort sort);
    /*
        var user = Sort.sort(User.class);
        var users = userrepo
                            .findByLevel(3,user.by(User::getRegistrationDate).descending());

     */
    List<User> findByActive(boolean active, Pageable pageable);
    Streamable<User> findByLevel(int level);
    Streamable<User> findByEmail(String email);
    /*
        Stream<User> result = repo.findByLevel(2)
                                    .and(repo.findByEmail("email"))
                                    .stream()
                                    .distinct();
     */

    @Query("select count(u) from User u where u.active= ?1")
    int findNumberOfUsersByActivity(boolean active);

    @Query("select u from User u where u.active= :active and u.level= :level")
    List<User> findByActiveAndLevel(
            @Param("active")boolean active,
            @Param("level") int level);


    //@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM USERS WHERE ACTIVE= ?1")
    //int findNumberOfUsersByActivityNative(boolean active);


    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);
    List<Projection.UsernameOnly> findByLevelLessThan(int level);


}
