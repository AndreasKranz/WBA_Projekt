package main.wba_projekt.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<PK extends Serializable> {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private PK id;

    @Override
    public int hashCode() {
        if (getId() != null){
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        if(!(obj instanceof BaseEntity<?>)) {
            return false;
        }

        BaseEntity<?> other = (BaseEntity<?>) obj;
        return id != null && id.equals(other.id);
    }
}
