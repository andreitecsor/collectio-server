package com.greenhabits.domain.node;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Challenge")
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "title")
    private String title;

    @Property(value = "brief")
    private String brief;

    @Property(value = "description")
    private String description;

    @Property(value = "mediaLink")
    private String mediaLink;

    public Challenge() {
    }

    public Challenge(String title, String brief, String description, String mediaLink) {
        this.title = title;
        this.brief = brief;
        this.description = description;
        this.mediaLink = mediaLink;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (id != null ? !id.equals(challenge.id) : challenge.id != null) return false;
        if (title != null ? !title.equals(challenge.title) : challenge.title != null) return false;
        if (brief != null ? !brief.equals(challenge.brief) : challenge.brief != null) return false;
        if (description != null ? !description.equals(challenge.description) : challenge.description != null)
            return false;
        return mediaLink != null ? mediaLink.equals(challenge.mediaLink) : challenge.mediaLink == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (mediaLink != null ? mediaLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", description='" + description + '\'' +
                ", mediaLink='" + mediaLink + '\'' +
                '}';
    }
}
