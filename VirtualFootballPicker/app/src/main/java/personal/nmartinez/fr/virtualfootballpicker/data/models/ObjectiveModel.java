package personal.nmartinez.fr.virtualfootballpicker.data.models;

public class ObjectiveModel {

    private int id;
    private String name;
    private int period;
    private boolean isEditable;

    public ObjectiveModel() {}

    public ObjectiveModel(ObjectiveModelBuilder builder) {
        this.id = builder.id;
        this.period = builder.period;
        this.name = builder.name;
        this.isEditable = builder.isEditable;
    }

    public static ObjectiveModel fromObjective(Objective objective) {
        return new ObjectiveModelBuilder().id(objective.getId())
                .isEditable(objective.isEditable())
                .name(objective.getName())
                .period(objective.getPeriod())
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public Objective toObjective() {
        return new Objective.ObjectiveBuilder().id(id).isEditable(isEditable).name(name).period(period).build();
    }

    public static class ObjectiveModelBuilder {
        private int id;
        private String name;
        private int period;
        private boolean isEditable;

        public ObjectiveModelBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ObjectiveModelBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ObjectiveModelBuilder period(int period) {
            this.period = period;
            return this;
        }

        public ObjectiveModelBuilder isEditable(boolean isEditable) {
            this.isEditable = isEditable;
            return this;
        }

        public ObjectiveModel build() {
            return new ObjectiveModel(this);
        }
    }
}
