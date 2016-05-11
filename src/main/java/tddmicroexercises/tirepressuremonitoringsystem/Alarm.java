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
        this(new Sensor());
    }

    public void check() {
        double psiPressureValue = getPressureValue();

        if (isNotInSafetyRange(psiPressureValue)) {
            alarmIsOn();
        }
    }

    private void alarmIsOn() {
        alarmOn = true;
    }

    private boolean isNotInSafetyRange(double psiPressureValue) {
        return (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue);
    }

    private double getPressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
