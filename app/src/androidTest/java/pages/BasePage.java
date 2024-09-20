package pages;

import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.StaleObjectException;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import android.util.Log;
import android.widget.Toast;

import androidx.test.internal.util.LogUtil;
import androidx.test.platform.app.InstrumentationRegistry;

//import com.zhiyi.autotest.app.ExampleInstrumentedTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import common.Init;

public class BasePage {

    public static final UiDevice mDevice = Init.mDevice;

    /*
    自定义查找单个元素，15s后超时
     */
    public UiObject2 findObject(BySelector by){
        long l = System.currentTimeMillis();
        UiObject2 object2 = null;
        while(true){
            object2 = mDevice.findObject(by);
            if(object2!=null||System.currentTimeMillis()-l>15000){
                return object2;
            }
            Log.i("FindUiObject2",by.toString()+"正在查找元素！！！");
        }
    }

    /*
    自定义查找多个元素，15s后超时
     */
    public List<UiObject2> findObjects(BySelector by){
        long l = System.currentTimeMillis();
        //ArrayList<UiObject2> list = new ArrayList<UiObject2>();
        while(true){
            List<UiObject2> list = mDevice.findObjects(by);
            if(list.size()!=0||System.currentTimeMillis()-l>15000){
                return list;
            }
            Log.i("FindUiObject2",by.toString()+"正在查找元素！！！");
        }
    }

    public void stepProtect(ExceptionProtect e){
        long l = System.currentTimeMillis();
        while(true){
            try{
                e.run();
            }catch (IndexOutOfBoundsException e2){
                e2.printStackTrace();
                if(System.currentTimeMillis()-l>10000){
                    break;
                }
                continue;
            }catch (StaleObjectException s){
                s.printStackTrace();
                if(System.currentTimeMillis()-l>10000){
                    break;
                }
                continue;
            }
            break;
        }
    }

    public void stepProtect(UiObjectProtect e){
        long l = System.currentTimeMillis();
        boolean b = false;
        while(true){
            try{
                b = e.run();
            }catch (StaleObjectException s){
                continue;
            }
            if(b||System.currentTimeMillis()-l>10000){
                break;
            }
        }
    }
}
