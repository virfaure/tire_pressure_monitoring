import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.SafetyRange;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_pressure_is_too_low() { // flaky test!!
        Alarm alarm = create_alarm_with_sensor_and_safety_range(5.0, new SafetyRange(10.0, 20.0));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }


    @Test
    public void alarm_is_on_when_pressure_is_too_high(){
        Alarm alarm = create_alarm_with_sensor_and_safety_range(30.0, new SafetyRange(5.0, 11.0));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_pressure_is_in_safety_range(){
        Alarm alarm = create_alarm_with_sensor_and_safety_range(20.0, new SafetyRange(10.0, 25.0));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_using_sensor(){
        Sensor sensor = mock(Sensor.class);
        Alarm alarm = new Alarm(sensor, new SafetyRange(10.0, 25.0));

        alarm.check();

        verify(sensor).pobreValue();
    }

    private Alarm create_alarm_with_sensor_and_safety_range(double pressure, SafetyRange safetyRange) {
        Sensor sensor = create_sensor_returning_pressure(pressure);
        return new Alarm(sensor, safetyRange);
    }

    public Sensor create_sensor_returning_pressure(double value){
        Sensor sensor = mock(Sensor.class);
        doReturn(value).when(sensor).pobreValue();

        return sensor;
    }
}
