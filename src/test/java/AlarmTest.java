import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_pressure_is_too_low() { // flaky test!!
        FakeAlarm alarm = new FakeAlarm(5.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_on_when_pressure_is_too_high(){
        FakeAlarm alarm = new FakeAlarm(30.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_pressure_is_in_safety_range(){
        FakeAlarm alarm = new FakeAlarm(20.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    public class FakeAlarm extends Alarm{
        private double pressure;

        public FakeAlarm(double pressure){
            this.pressure = pressure;
        }

        @Override
        protected double getPressureValue(){
            return this.pressure;
        }
    }
}
