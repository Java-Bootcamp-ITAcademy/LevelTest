package Owner;

import Vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Vehicle Owner class */
public class Owner {
    private String name;
    private String id;
    private List<Vehicle> vehicles;

    public Owner(String name, String id) {
        this.name = name;
        this.id = id;
        this.vehicles = new ArrayList<Vehicle>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner owner)) return false;
        return id.equals(owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
