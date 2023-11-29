package com.example.leesplezier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SessionKey implements Serializable {

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "user_id")
    private Long userId;

    public SessionKey() {
    }

    public SessionKey(Long childId, Long userId) {
        this.childId = childId;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() !=obj.getClass()) return false;
        SessionKey that  = (SessionKey) obj;
        return childId.equals(that.childId) && userId.equals(that.userId);

    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
