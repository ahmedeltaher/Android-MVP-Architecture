
package com.task.data.remote.dto;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Scooter> scooters = new ArrayList<Scooter>();

    public List<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(List<Scooter> scooters) {
        this.scooters = scooters;
    }

}
