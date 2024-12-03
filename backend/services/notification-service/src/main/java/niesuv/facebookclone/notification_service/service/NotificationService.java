package niesuv.facebookclone.notification_service.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import niesuv.facebookclone.notification_service.entity.Notification;
import niesuv.facebookclone.notification_service.exception.CannotSaveNewNotificationException;
import niesuv.facebookclone.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public UUID addNotification(Notification notification) {
        Notification saveNotification = notificationRepository.save(notification);
        if (saveNotification.getId() != null) {
            return saveNotification.getId();
        }
        throw new CannotSaveNewNotificationException("Cannot create new notification");

    }
}
