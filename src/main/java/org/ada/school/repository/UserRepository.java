package org.ada.school.repository;

import org.ada.school.model.IUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    List<IUser> findByNameLikeOrLastNameLike(String like1, String like2);

    List<IUser> findByCreatedAtAfter(Date startDate);
}
