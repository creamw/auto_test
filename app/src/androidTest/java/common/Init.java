package common;

import androidx.test.uiautomator.Configurator;
import androidx.test.uiautomator.UiDevice;

import androidx.test.platform.app.InstrumentationRegistry;

public class Init {
    public static final UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static final Configurator confg = Configurator.getInstance();

    static {
        confg.setActionAcknowledgmentTimeout(0);//默认3000毫秒
        confg.setWaitForIdleTimeout(0);//默认10000毫秒
    }


}
