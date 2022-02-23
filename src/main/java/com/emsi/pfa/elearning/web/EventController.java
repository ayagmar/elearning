package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Event;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/events")
@Api(value = "Events operations", description = "Operations pertaining to events in Nejtrans Application")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Used by Admin to view a list of all events")
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAll() {
        return eventService.getAll();
    }

    @ApiOperation(value = "Used by admin to create a new event for a specific user")
    @PostMapping("/create")
    public ResponseEntity<String> CreateEvent(@RequestBody FormHelperClass.UserEventForm form){
        return eventService.CreateEvent(form);
    }
}
