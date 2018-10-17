package cn.footman.springbootjpa.repository;

import cn.footman.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author footman77
 * @create 2018-09-25 14:26
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
