package com.vee.Blogapp.payloads;

import com.vee.Blogapp.entites.Post;
import com.vee.Blogapp.entites.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class CommentDto {
    private int id;
    @NotNull(message = "Comment missing")
    private String comment;
    private boolean isDeleted=false;
    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
