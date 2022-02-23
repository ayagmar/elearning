package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.EventRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.Event;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok().body(eventRepository.findAll());
    }

    public ResponseEntity<String> CreateEvent(FormHelperClass.UserEventForm form){
        LocalDateTime dateS = LocalDateTime.parse(form.getDateStart());
        LocalDateTime dateE = LocalDateTime.parse(form.getDateEnd());
        if (dateS.isAfter(dateE) ||
                dateE.isEqual(dateS) ||
                dateE.isBefore(LocalDateTime.now()) ||
                dateS.isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Your date does not fit criteria");
        }
        Event event=new Event();
        event.setEventUser(userRepository.findByUsername(form.getUsername()));
        event.setDescription(form.getDescription());
        event.setTitle(form.getTitle());
        event.setStartDate(dateS);
        event.setEndDate(dateE);
        eventRepository.save(event);
        return ResponseEntity.ok().body("Created Succesffuly");
    }
}
