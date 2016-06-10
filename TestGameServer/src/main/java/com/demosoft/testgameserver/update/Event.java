package com.demosoft.testgameserver.update;


import com.demosoft.testgameserver.map.enity.Point;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
public class Event {

    enum EventType {
        PLAYER(PlayerEvent.class), MAP(MapEvent.class);

        Class<? extends Event> aClass;

        EventType(Class aClass) {
            this.aClass = aClass;
        }

        public void setaClass(Class<? extends Event> aClass) {
            this.aClass = aClass;
        }

        public Class<? extends Event> getaClass() {
            return aClass;
        }

        public <T extends Event> T getEvent(Class<? extends Event> c, Event event) {
            return (T) event;
        }
    }

    private EventType eventType;
    private long number;

    public long getNumber() {
        return number;
    }

    void setNumber(long number) {
        this.number = number;
    }

    public EventType getEventType() {
        return eventType;
    }

    void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (number != event.number) return false;
        return eventType == event.eventType;

    }

    @Override
    public int hashCode() {
        int result = eventType != null ? eventType.hashCode() : 0;
        result = 31 * result + (int) (number ^ (number >>> 32));
        return result;
    }


    public static class PlayerEvent extends Event {

        Point start;
        Point end;
        boolean moved;

        public PlayerEvent() {
            setEventType(EventType.PLAYER);
        }

        public Point getStart() {
            return start;
        }

        public void setStart(Point start) {
            this.start = start;
        }

        public Point getEnd() {
            return end;
        }

        public void setEnd(Point end) {
            this.end = end;
        }

        public boolean isMoved() {
            return moved;
        }

        public void setMoved(boolean moved) {
            this.moved = moved;
        }
    }

    public static class MapEvent extends Event {

    }
}
