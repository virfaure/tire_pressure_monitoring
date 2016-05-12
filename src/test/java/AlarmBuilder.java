import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.SafetyRange;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

public class AlarmBuilder {

    private Sensor sensor;
    private SafetyRange safetyRange;

    public AlarmBuilder usingSensor(Sensor sensor){
        this.sensor = sensor;

        return this;
    }

    public AlarmBuilder withSafetyRange(double lowValue, double highValue){
        this.safetyRange = new SafetyRange(lowValue, highValue);

        return this;
    }

    public static AlarmBuilder anAlarm(){
        return new AlarmBuilder();
    }

    public Alarm build(){
        return new Alarm(sensor, safetyRange);
    }
}
