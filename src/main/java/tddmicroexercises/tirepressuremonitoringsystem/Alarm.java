package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor;

    private boolean alarmOn = false;

    public Alarm(Sensor sensor){
        this.sensor = sensor;
    }

    public Alarm(){
        this(new PressureTelemetrySensor());
    }

    public void check() {
        double probeValue = getProbeValue();

        if (isNotInSafetyRange(probeValue)) {
            alarmIsOn();
        }
    }

    private void alarmIsOn() {
        alarmOn = true;
    }

    private boolean isNotInSafetyRange(double probeValue) {
        return (probeValue < LowPressureThreshold || HighPressureThreshold < probeValue);
    }

    private double getProbeValue() {
        return sensor.pobreValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
