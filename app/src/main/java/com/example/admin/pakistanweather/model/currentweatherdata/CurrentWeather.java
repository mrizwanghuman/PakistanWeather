
package com.example.admin.pakistanweather.model.currentweatherdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

}
