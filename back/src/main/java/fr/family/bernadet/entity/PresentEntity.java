package fr.family.bernadet.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRESENT")
@Getter
@Setter
public class PresentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromUser;
    private String toUser;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.fromUser);
        hash = 79 * hash + Objects.hashCode(this.toUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PresentEntity other = (PresentEntity) obj;
        if (!Objects.equals(this.fromUser, other.fromUser)) {
            return false;
        }
        if (!Objects.equals(this.toUser, other.toUser)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", from='").append(fromUser).append('\'');
        sb.append(", to=").append(toUser);
        sb.append('}');
        return sb.toString();
    }
}
