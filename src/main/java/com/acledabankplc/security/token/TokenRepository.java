package com.acledabankplc.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
//@Query(value = """
//        SELECT t.*
//        FROM token t
//        INNER JOIN "user" u ON t.user_id = u.id
//        WHERE u.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)
//    """, nativeQuery = true)
  List<Token> findAllValidTokenByUser(Integer id);

  Optional<Token> findByToken(String token);
}
