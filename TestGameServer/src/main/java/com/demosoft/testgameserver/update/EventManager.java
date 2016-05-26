package com.demosoft.testgameserver.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
public class EventManager {

    private HashMap<Long, Event> events = new HashMap<>();
    private Long currentSnapshot = -1l;


    public Event pushNewEvent(Event event) {
        synchronized (this) {
            currentSnapshot++;
        }
        event.setNumber(currentSnapshot);
        events.put(currentSnapshot, event);
        return event;
    }

    public boolean isHasUpdates(long snapshot) {
        return currentSnapshot != snapshot;
    }

    public List<Event> getUpdates(long snapshot) {
        List<Event> events = new ArrayList<>();
        if (!isHasUpdates(snapshot)) {
            return events;
        }
        long updatesCount = currentSnapshot - snapshot;
        for (int i = 1; i <= updatesCount; i++) {
            events.add(this.events.get(snapshot + i));
        }
        return events;
    }

    public Long getCurrentSnapshot() {
        return currentSnapshot;
    }
}
