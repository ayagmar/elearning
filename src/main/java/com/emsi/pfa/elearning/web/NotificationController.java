package com.emsi.pfa.elearning.web;


import com.emsi.pfa.elearning.model.Notification;
import com.emsi.pfa.elearning.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private NotificationService notificationService;

    @GetMapping("/all")
    public List<Notification> getAll(){
        return notificationService.getAllNotifications();
    }

    @PutMapping("/{id}/read")
    public void setRead(@PathVariable Long id){
        notificationService.setRead(id);
    }
}
