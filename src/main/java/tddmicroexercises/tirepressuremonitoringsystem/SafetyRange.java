package tddmicroexercises.tirepressuremonitoringsystem;

public class SafetyRange {
    private final double lowPressureThreshold;
    private final double highPressureThreshold;

    public SafetyRange(double lowPressureThreshold, double highPressureThreshold) {
        this.lowPressureThreshold = lowPressureThreshold;
        this.highPressureThreshold = highPressureThreshold;
    }

    public boolean includes(double probeValue) {
        return (probeValue < lowPressureThreshold || highPressureThreshold < probeValue);
    }
}
