package DAY_2;

import java.util.ArrayList;
import java.util.List;

public class Challenge3 {
    public static void main(String[] args) {
        
        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Sydney Town Hall", UsageType.GOVERNMENT));
        mappables.add(new Building("Sydney Opera House", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Stadium Australia", UsageType.SPORTS));

        for(var m : mappables){
            Mappable.mapIt(m);
        }
    }
}

enum Geometry {LINE, POINT, POLYGON}

enum UsageType {GOVERNMENT, ENTERTAINMENT, RESIDENTIAL, SPORTS}

enum Color {BLACK, RED, BLUE, GREEN, ORANGE}

enum PointMarker {CIRCLE, PUSH_PIN, STAR, SQUARE, TRAINGLE}

enum UtlityType {ELECTRICAL, FIBRE_OPTIC, GAS, WATER}

enum LineMarker {DASHED, DOTTED, SOLID}

interface Mappable {

    String JSON_PROPERTY = """
            "Property": {%s} """;

    String getLabel();
    String getMarker();
    Geometry getShape();

    default String toJSON() {

        return """
                "type": "%s", "label": "%s", "marker": "%s"
                """.formatted(getShape(), getLabel(), getMarker());
    }

    static void mapIt(Mappable mappable) {
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }
}

class Building implements Mappable {

    private String name;
    private UsageType usage;

    public Building(String name, UsageType usage) {

        this.name = name;
        this.usage = usage;
    }

    @Override 
    public String getLabel() {
        return name + " (" + usage + ")";
    }
    @Override 
    public Geometry getShape() {
        return Geometry.POINT;
    }
    @Override 
    public String getMarker() {

        return switch(usage) {

            case ENTERTAINMENT -> Color.GREEN + " " + PointMarker.TRAINGLE;
            case GOVERNMENT -> Color.RED + " " + PointMarker.STAR;
            case RESIDENTIAL -> Color.BLUE + " " + PointMarker.SQUARE;
            case SPORTS -> Color.ORANGE + " " + PointMarker.PUSH_PIN;
            default -> Color.BLACK + " " + PointMarker.CIRCLE;
        };
    }
    @Override 
    public String toJSON() {
        return Mappable.super.toJSON() + """
                "name": "%s", "usage": "%s"
                """.formatted(name, usage);
    }
}

class UtilityLine implements Mappable {

    private String name;
    private UtlityType type;

    public UtilityLine(String name, UtlityType type) {
        this.name = name;
        this.type = type;
    }

    @Override 
    public String getLabel() {
        return name + " (" + type + ")";
    }
    @Override 
    public Geometry getShape() {
        return Geometry.LINE;
    }
    @Override 
    public String getMarker() {

        return switch(type) {

            case ELECTRICAL -> Color.RED + " " + LineMarker.DASHED;
            case FIBRE_OPTIC -> Color.GREEN + " " + LineMarker.DOTTED;
            case GAS -> Color.ORANGE + " " + LineMarker.SOLID;
            case WATER -> Color.BLUE + " " + LineMarker.SOLID;
            default -> Color.BLACK + " " + LineMarker.SOLID;
        };
    }
    @Override 
    public String toJSON() {
        return Mappable.super.toJSON() + """
                "name": "%s", "utility": "%s"
                """.formatted(name, type);
    }
}
