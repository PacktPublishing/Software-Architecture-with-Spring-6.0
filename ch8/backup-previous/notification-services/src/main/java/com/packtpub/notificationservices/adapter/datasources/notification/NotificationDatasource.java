package com.packtpub.notificationservices.adapter.datasources.notification;

import com.packtpub.notificationservices.internal.entity.Notification;
import com.packtpub.notificationservices.internal.repositories.NotificationRepository;

public class NotificationDatasource implements NotificationRepository {

    private final NotificationDocumentRepository notificationDocumentRepository;

    public NotificationDatasource(NotificationDocumentRepository notificationDocumentRepository) {
        this.notificationDocumentRepository = notificationDocumentRepository;
    }

    @Override
    public Notification save(Notification notification) {
        NotificationDocument notificationDocument = new NotificationDocument(null, notification.getAuction(), notification.getEmails());
        NotificationDocument notificationDocumentSaved = notificationDocumentRepository.save(notificationDocument);
        notification.setId(notification.getId());
        return notification;
    }
}
