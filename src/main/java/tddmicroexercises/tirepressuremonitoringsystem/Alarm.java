package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor;
    private SafetyRange safetyRange;

    private boolean alarmOn = false;

    public Alarm(Sensor sensor, SafetyRange safetyRange){
        this.sensor = sensor;
        this.safetyRange = safetyRange;
    }

    public Alarm(){
        this(new PressureTelemetrySensor(), new SafetyRange(17, 21));
    }

    public void check() {
        double probeValue = getProbeValue();

        if (isNotInSafetyRange(probeValue)) {
            alarmIsOn();
        }
    }

    private boolean isNotInSafetyRange(double probeValue){
        return this.safetyRange.includes(probeValue);
    }

    private void alarmIsOn() {
        alarmOn = true;
    }

    private double getProbeValue() {
        return sensor.pobreValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
