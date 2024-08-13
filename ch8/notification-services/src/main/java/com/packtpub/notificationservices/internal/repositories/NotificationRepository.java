package com.packtpub.notificationservices.internal.repositories;

import com.packtpub.notificationservices.internal.entity.Notification;

public interface NotificationRepository {
    Notification save(Notification notification);
}

