package com.example.monumentos_backend.model;

import java.io.Serializable;
import java.util.Objects;

public class ScoreId implements Serializable {
    private String id;
    private String routeId;

    public ScoreId() {
    }

    public ScoreId(String id, String routeId) {
        this.id = id;
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScoreId scoreId = (ScoreId) o;
        return Objects.equals(id, scoreId.id) && Objects.equals(routeId, scoreId.routeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId);
    }
}
