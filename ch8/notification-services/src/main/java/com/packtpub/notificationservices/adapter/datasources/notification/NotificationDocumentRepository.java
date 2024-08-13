package com.packtpub.notificationservices.adapter.datasources.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDocumentRepository extends MongoRepository<NotificationDocument, String> {

}
