package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.EventRepository;
import com.emsi.pfa.elearning.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok().body(eventRepository.findAll());
    }
}
