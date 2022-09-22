package com.datacom.api;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

@Container(containerName = "attendees")
public class Attendee {

    @Id
    @JsonProperty(value="id")
    private String id;

    @PartitionKey
    @JsonProperty(value="email")
    private String email;

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="company")
    private String company;

    @JsonProperty(value="linkedInURL")
    private String linkedInURL;

    @JsonProperty(value="checkedIn")
    private boolean checkedIn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLinkedInURL() {
        return linkedInURL;
    }

    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
