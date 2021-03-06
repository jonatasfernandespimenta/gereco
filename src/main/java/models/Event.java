package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Event {
    private String id;
    private String name;
    private LocalDate date;
    private EventStatus eventStatus;
    private List<Modality> modalities;
    private List<List<GeneralMatch>> matches;

    public Event(){
        this("", new ArrayList<>());
    }

    public Event(String name, List<Modality> modalities) {
        this.name = name;
        this.date = LocalDate.now();
        this.eventStatus = EventStatus.PLANNING;
        this.modalities = modalities;
        this.matches = Arrays.asList(new ArrayList<>(), new ArrayList<>());
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
    public LocalDate getDate() {
        return date;
    }
    public List<List<GeneralMatch>> getMatches() {
        return matches;
    }
    public void setMatches(List<List<GeneralMatch>> matches) {
        this.matches = matches;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Modality> getModalities() {
        return modalities;
    }
    public void setModalities(List<Modality> modalities) {
        this.modalities = modalities;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
