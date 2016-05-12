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
    private Alarm alarm;

    @Test
    public void alarm_is_on_when_pressure_is_too_low() { // flaky test!!
        alarm = AlarmBuilder.anAlarm().
                usingSensor(thatProbes(5.0)).
                withSafetyRange(10.0, 20.0).
                build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }


    @Test
    public void alarm_is_on_when_pressure_is_too_high(){
        alarm = AlarmBuilder.anAlarm().
                usingSensor(thatProbes(30.0)).
                withSafetyRange(5.0, 11.0).
                build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_pressure_is_in_safety_range(){
        alarm = AlarmBuilder.anAlarm().
                usingSensor(thatProbes(20.0)).
                withSafetyRange(10.0, 25.0).
                build();

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

    public Sensor thatProbes(double value){
        Sensor sensor = mock(Sensor.class);
        doReturn(value).when(sensor).pobreValue();

        return sensor;
    }
}
