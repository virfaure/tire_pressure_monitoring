import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_pressure_is_too_low() { // flaky test!!
        Alarm alarm = create_alarm_with_sensor(5.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }


    @Test
    public void alarm_is_on_when_pressure_is_too_high(){
        Alarm alarm = create_alarm_with_sensor(30.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_pressure_is_in_safety_range(){
        Alarm alarm = create_alarm_with_sensor(20.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_using_sensor(){
        Sensor sensor = mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        verify(sensor).popNextPressurePsiValue();
    }

    private Alarm create_alarm_with_sensor(double pressure) {
        Sensor sensor = create_sensor_returning_pressure(pressure);
        return new Alarm(sensor);
    }

    public Sensor create_sensor_returning_pressure(double value){
        Sensor sensor = mock(Sensor.class);
        doReturn(value).when(sensor).popNextPressurePsiValue();

        return sensor;
    }
}
