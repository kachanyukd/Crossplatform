package converter;

public class ConverterService {

    public static double convert(String type, String from, String to, double value) {

        switch (type) {

            case "Time":
                return timeConvert(from, to, value);

            case "Distance":
                return distanceConvert(from, to, value);

            case "Speed":
                return speedConvert(from, to, value);

            case "Mass":
                return massConvert(from, to, value);

            case "Area":
                return areaConvert(from, to, value);

            case "Temperature":
                return temperatureConvert(from, to, value);

            case "Pressure":
                return pressureConvert(from, to, value);

            case "Volume":
                return volumeConvert(from, to, value);

            case "Energy":
                return energyConvert(from, to, value);

            default:
                return value;
        }
    }

    private static double timeConvert(String from, String to, double v) {
        double seconds = switch (from) {
            case "Minutes" -> v * 60;
            case "Hours" -> v * 3600;
            default -> v;
        };

        return switch (to) {
            case "Minutes" -> seconds / 60;
            case "Hours" -> seconds / 3600;
            default -> seconds;
        };
    }

    private static double distanceConvert(String from, String to, double v) {
        double meters = switch (from) {
            case "Kilometers" -> v * 1000;
            case "Miles" -> v * 1609.34;
            default -> v;
        };

        return switch (to) {
            case "Kilometers" -> meters / 1000;
            case "Miles" -> meters / 1609.34;
            default -> meters;
        };
    }

    private static double speedConvert(String from, String to, double v) {
        double mps = switch (from) {
            case "km/h" -> v / 3.6;
            case "mph" -> v * 0.44704;
            default -> v;
        };

        return switch (to) {
            case "km/h" -> mps * 3.6;
            case "mph" -> mps / 0.44704;
            default -> mps;
        };
    }

    private static double massConvert(String from, String to, double v) {
        double kg = switch (from) {
            case "Grams" -> v / 1000;
            case "Pounds" -> v * 0.453592;
            default -> v;
        };

        return switch (to) {
            case "Grams" -> kg * 1000;
            case "Pounds" -> kg / 0.453592;
            default -> kg;
        };
    }

    private static double areaConvert(String from, String to, double v) {
        double m2 = switch (from) {
            case "Hectares" -> v * 10000;
            case "Acres" -> v * 4046.86;
            default -> v;
        };

        return switch (to) {
            case "Hectares" -> m2 / 10000;
            case "Acres" -> m2 / 4046.86;
            default -> m2;
        };
    }

    private static double temperatureConvert(String from, String to, double v) {
        if (from.equals("Celsius") && to.equals("Fahrenheit"))
            return v * 9 / 5 + 32;

        if (from.equals("Fahrenheit") && to.equals("Celsius"))
            return (v - 32) * 5 / 9;

        return v;
    }

    private static double pressureConvert(String from, String to, double v) {
        double pa = switch (from) {
            case "Bar" -> v * 100000;
            case "PSI" -> v * 6894.76;
            default -> v;
        };

        return switch (to) {
            case "Bar" -> pa / 100000;
            case "PSI" -> pa / 6894.76;
            default -> pa;
        };
    }

    private static double volumeConvert(String from, String to, double v) {
        double liters = switch (from) {
            case "Cubic meters" -> v * 1000;
            case "Gallons" -> v * 3.78541;
            default -> v;
        };

        return switch (to) {
            case "Cubic meters" -> liters / 1000;
            case "Gallons" -> liters / 3.78541;
            default -> liters;
        };
    }

    private static double energyConvert(String from, String to, double v) {
        double joules = switch (from) {
            case "Calories" -> v * 4.184;
            case "kWh" -> v * 3600000;
            default -> v;
        };

        return switch (to) {
            case "Calories" -> joules / 4.184;
            case "kWh" -> joules / 3600000;
            default -> joules;
        };
    }
}