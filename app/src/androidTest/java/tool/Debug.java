package tool;

import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

//import androidx.test.espresso.action.KeyEventAction;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiObject2;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pages.BasePage;
import pages.ExceptionProtect;

public class Debug extends BasePage {

    @Test
    public void test_go(){
        String x = "cDRWR1VFd3Q1NVYwTDVQQmhUc1pqcGp0TmxMKzlBL3RXc2paUGNFTzIwSXRMcGYxRjFFYksyM05QWnpRL2E5UHAyVTRVNWhOWWdnQU81Y2Z5cm5ZVmtFNzlDbnlGMDNxa0c2TnNKTmFrTkNudlNwN2xiaGt1ZnVVbmFadEw4SnhzMXNncFdvWGRDUlF6eDJFeUpKK3VJVVBJVThDOHMvNmdBVHNQMndHVXZheCtRZllSNGVZblZodWxiOWNmTHZwTUhGdjRoOUw2TkE3emhHeWlrTmYwTG1xb3pSZmhDMjY3ZkRFWjMxOGlLQTZ3ZkRYRk1TVmJSYWVPQTliMmx4eldFMkxnQ0JWNjE3eEoxMTd5ZFMzNE9NNVVyZnE0aVdhM2RBOE1ZSThVV1RPYm1JSk81WmZBRU92b2RJUEFUZ0FxZEtkb3kxNU5RdG5RSVU1aTlhd2xoYjFtQ3VEZWt6bWNIMEp4KzI0ZUFhS3l3MmJuMTFQcklobnhSVlJrZ0lYb1FlLzMyQ1RCMW1ubXFnMjVZdDhWRlEyeXpQeUhIY0tqWnN6cjhFR0E4NVJpb0lNbmRWSjAyYWVBOFZQQnYxUVlhZUx4M281d1VZMmxZUDI0OFRwYzliRHVJTXF3WlVvNXYvaDcwVEo1MXNoNDhONHd4dVd6dWxHSjl5SUEyVHVwRldkM0hNYUdReUJuenRGckJ4bFE1WHNLRFJUd1RLUW0vcDBhSWZ3bExCUFJybFhaVERQenQ2anNLcGJ4dEIvQ1FtYXFKWlZRcW1PTGZVU2htc0tqVTRGSU9ucjY2RzhvVmc3QXRtaElqZWxZYTlvVUpOaGlzRGh2aGlKTWtGTzBYcHVJUnJnWDhwUGtvTWhLbUNQbnZTNjNBS0V2TkNaTExKdFpOTDd3Ym9sa1JYK1lnR1dQNzFmRmRnOVRRZGZmUFhNK0pkc0U3d2M0UUd1dVovZWV3aE9wNUJoQ3k2T2Z4M3ZNSTlac3g0REVWVkVWK0ZGSmNxQWtaOXFkK2dVc0MwOGZqbEFNSTZzU3hjcWxDQW5QY3cxTkpTUGVpQ21MSUZxWnRYQVpOUmtEK2xvaWlTV1FIQlF2WStRNThVaEl1amEzUVpvVWgwbXFXaXdXVlFzWGQ2cFlFZnlhNUhOYUFnMmR2L1ZjOUhEUExtVjNQV3NXVVBpTTFDcE5TZmxnc2Rtb0E4Y3g3dkNnTjg1dWI3b0NKcVdIZVR3MVhieEc2b01tYzBNeVZUNE1jV01CUW4wTXlpSDZLZUpvSWlFenRPRlZyd1V5bjU5aVNrODdXME92eXoyL29rUjZWZGQ4QUlzcTBmams5UnhkZmdqZVdWNUEvcnQxL0pxS0RYR1RLaHBVQ054YUo5NjBZS0xrZ2tFcFd4WFE2blhaMmJIYjlOVUJVYlpRMFFlMFUxUUJ0K05obllGQzR2TkUyNWFOaFFiNXJCUHVDd2JCb1JZZWNkSHU2VkpuQjFXUkhzc2hhby8zNktWT1p3eHNxcVo5VEppZ2JIZkowMWQ5aFVhYVpPc0lVZWlKZXhMeStVYzJhRmxUYnJPMnJ0MExWWWQweWtEaEVUdmx1SzVWV091Y2tJVGY3L05wWDBWdEFEQVFiUEFGWU05Zm0rSG9aWWRHOThNWmo3MzJ3R2hEQ1Mya1VKRGdQbG9jQ29oUnZnWjhtUS9vbVk5UjgvOXJrQmpMeVo2TTNGejVycElPa3JsVWdCNy9rQTZYdkJhbmVYTE9oMnBEWnB2V3ZNVVhrVndOR0xIbVcxWlhMNTNKYnhHV3hnVXdOeC9wMTMvc3ZqaEEzVHVIUExqUGhsckExbFkrWkI1QkdCNVc1ZExKVy9OMkVNRUhYblVSSWxxZ1dRVXpvdEhzWkpWeVlFTVNJbGU5Zkw0TGE3QWhPZEpqWDZoZFFFaG5rZ3BHa0dkQk4rdUh6bEpvUG5RWEI2eUFkajVRc3k0NHdmeXRqQTVKK1Nsb2tmankvSDdEK0lDVmdtUW9vK2Flbko4OG5SZzg2R1o1b3kxTmMrZXFQN1lPSXFsMy9FdXdGVHVGV3piblEwNkdZZ3UxendvQWVDU2dTNDJuR09CV3BCellVZDAyTDlCdkJqWnBNTng2NFZueWpFVnRYNWEyUlhOYzRyVXlhTTBzZHRTdXlKVlJMTElwd2sydzdpQmlVZ2tXRi9iYWpJZ2FvaHAzVlVoVVhHVDY4b281Vk9vRTJQeWVlRG5SRmdob1kyaXY5R3ZHMTZJSWhsSDAzMTJVOVVCOVJ4aVgyMmxObllDY0FsbVc1WC9VUU9ZOVhtZExrWnZNbHY0Mk1hM2EyNk9yYjdTdXRXbU5wK05NcUxzK3lZQUVtK1FITEVFUW8rdXhGVFRjWmlBL3RHUlVER2FTeDQ5a2NTNmR2WVV3OVVmT0FQT1gxeHVpWFZ3anp6MXJSNzNScnljdkRobDRHc1dGOGJ4RUJYVU95WWZRYnQ1QmtYVDBFdXl3TC9IUTZiZjgvcmYxYlBCU1Q1U3NjQVZYQTR0OHMyT3NNQkNMQXR1aTlVSGpGeE53bFB3eUxwRW1mNWhxTGw5ei9GRVBndUpVVU8zWmFBVy8zbCtNbDg2NkZwclN1Y2tzMmFkMU1Id2llWWFNbDgxYmR0VTE5eklFdTRwZEVHRXRRM0RnMDBDN2RhS0tmN1ZmVDFmeGZiUzRFNEpnSzU0TktqajNvb3QzdElNaE9EdFdMQ0ZzVzVJbHRHNG40a2pvdHBkK1BVTHNUQk5FNE13NEo5cXpxalRFeFlJQzJJN3M5VnFGSWx4YzhyNjFlc3ZzbTlRRmRTUGRWOTdLODZ2Vm9CYWRsUXN2Vk4xOXo5YzJ1REV2WXJUSHZOTmNSa1B5NDh3Vlk0KzkzWjBJZHdnN3ZjY3o4aUVkM2IvRDI2ejRIajVPQURrVE9rb3V0SGlKc1RjQjhtUWFFVHRxaGZOQkduVG9EUnNsSlJmQUUxc0xVeHllOVBWMUwyZG5nYjJjY2Q5QWZ2bURVUlFDV0g3QXI5R3lsZWc1cmJXemVhM3ZTcUNpVHBIekNGc25jNC9oeXp4VGE0VEpDTTQvVUZhRFozV3pBdkxPZzJpU3VxYmQ2cDNtZmVDZE9aUU1IQXRYVTBhWkNvWU1SZUtwWDkxQ29kbmtCZ2FZZXJSd0U3d0JpWkR4R1RzY0VxU2pQeUFEVEhUUUhabmlrNjZGZmwxRE4wcGw1emk4ZWNKZXNFdGVGRDY2dDVVRStvN2RjVGhDbDRkVndwQXB6L2gvWHM5UkNTL28zbVU2Qm0rZUp2TmozNFhDM3VkOTBzU2tYaUxqTDI3VWFZYU9TMHFtczhUYk8zQ3VhVG1uUEZIcXI2V3RQcGZGek05UnZlR0tMWUNvREEzSFNzK3l4aGxqMHNLREYxR0pXVS9rWlQ2QW94Z3BHYitUbzVhUDFzWWVlSWVKcDB0RWlxQzhsMUZUUm43ajRDL2lXRHpuSUpmbjU0akkvK0oxZ21naHFqTTRWMUV2dHI2OWhYTzJaTXRVMS8yVXFFUGFaV2VPWG5zZjhEcndNMVFjbFRocjM0RHRDWTYxSnM2Z2FRUkx0ZnQ2QU1xeVBoc1ZsV2I5NktlbkprUStnWUVqVGVUVWZ1WDRiN1I3MmVDeFIvV1p1V1RpR0xCTUNZVFp2N3FnWUFmczY4MldLYlFXVmdDS1BMN0lXVVl2Tk1GQ005cTMzeFpkcXE4cG10bXpJUDlwSUVhalJuSTNGTFQ4TkdzMFBPYkgzVVc1U3BrVCtGQmFRV3Q3ZFBscUs2Y294SDQzYmVLbml6QUxlUnk4b2l4a3NEUjF1QlhRVVRvazQ1WEpYeDhSQTJiY3pSbnowcUNLOTFYMGx0MXFVZ3hWdUhvRW1XR3FHelZRQVBMN3Q5eUgyS1ZBekluK3NNa0tzejJvblNNbkRZTXFpdVJPQ1Q3OSswV0ZwUjk3dnJIQS9OS3VJWlVuSkZ3amowQmllMm9KTEhWWFdrNTc1bW1oV25iQTN2MDhubU03d1RTNENSSjl4K2xYWmIrWG5mUk9mRVFJaVVRMDB1Rms0UUhRVVZrdDRwby9JM2NHbEwyQU5DbVkxaWhsVFdsT3dKb054UU5wWVdkejJjemlwZnFzQkR1M1ZJNmtuWnlVNGg3QUhtaVBoUFFJRnNXQXJVRHZNMzhTQTlxY1R4enp1T3hKSnlZSFZFQ2JVMWhldHdHRE9GRm9jS2hWVW9vQzI1Y2dvd0VneDllYlpGK29ZT0pyTStDdlhJWUZ4Rk9zdmV5TnhrR3hUY2FiVnFmOFJOUzM5b2ZTMVNDNVpwb0JFNjhVWjd0dXlQUlNhdzBRRUVSVlg5ZVZPK0J2cnBNZEZWeE5nbjhBVE9FbEQ3dU5BWWNYLzlDZ29hYkxORnhjTjBvN2RvZkdKbUZCVnkvSFNnZjR0aDNmdUtzTzRCcmJxSDFLdC85SXRjRU84WDU5T3l3STkvRDNobzhDeC8zM3FvWXZIQ3JvRG9lK0NFUnFldlA1TXdtNEZ6Sk96UTNnMkt0UUZST0pJclAwV1hqQzVkWjNLSUZ4NDJNVVdQNGJzYjZjR0ZvT0kzTHRhTy9YUzR3SlFESmZ2MnRUaVJOaTNRTGUwb0VmNjRNbG9lNzRBcjJESFQyZmFyZWRCUlkzU0VBc0h2MUJrRDNNTEUrbXJ4dTQ3dFBMOWNiNHVySWdxMGo1a291ZFRhSkxsckRrb2xmbFM2SjhRZFdJMDhRSmtDV1ZWclV4d1Z6dit6VHpadlNGM3U2UU5KMWpHa014ajJDTXNDaEtuZDhxSXlZV1BqR0h5S3NXaStuVk9uRk9vaHFaMzRTZW4ySEZXOWdUZjd3TS9PQW5MTk52YWk4NDU0N3kxYjRCbE5xSyt3R3J6Y0o2MUtSOGhjUkh3c2tRY2NKRzc4dzNqNWRZY0VNZ0VpT2xpWXJoWlJSd3lBY3pIYjdUalcyTm5QSDJJNG5QUWplQmpGdmpER2NlUVR1N2JZZ0dzMWl3Z3JhMEJSS3VzdkZISis3VE42djl2QzdZbklyZDFCYjZVRGdVdXc1enBaV0pFK05sekp6MUhXS1BVMkxpbzZBa1p5NjBYZ1RBZEpwQnhqbm1ZaTM0OFVtNURad3FEcHhrVy9WcVQyZUxaYVlyd3ZWV2RVeFN4a1BNZjVvQ3Q5R0Jqb3ZVbnVYQ3UyUTM2SWdlTzRyWThHUThNVkp5UlBHY3RkQmNweDcyckZOcE5ITjlJeHkwdXcrZTBsaHpWaVN1YzRMU2JkUWtPZmhoU0NISVIvNXE2Nkp2c0laTU1sZGdwMlpudks0aEZNWDVteWtSbytXZVNnZGxLTVIwek5XeGk1M2Z6MlI5SEp2N0cxdFRZeXFUcjFlRm51d0o1SE1JNWdWZG9uWjBEMk5OTXYrTmpYZzVHdDF2d1N2VVBUV3hBall3bSs5ek5FUkhweHV0ZCtWeGZWVHZ2Nzlpc0RLVDRGNWFrcXZZbElBZUZWb1pqWGNaSVlOeU5xR0x2SXhtYURzWUF0SjdhcmtkVFZtdGRWbDBsTTA1VmhxZEtkdHQ0VFQ3TWp3a0lLeGRCUG9MQWhmMGF1MDdZaFdIOEVJNFpLcVhlWVlnNUhNeEZ1Mk5YUElEeFJpV21pSVJFcHpSTkE4RjhpM0E1RDkwSHprRnFuaGttZjBxR1RhZ3ZIY0cyNU5MUE9YMGtneFhOcEwyeEVKWjJtR0lHbC9lVjZhQ2k2UEdNK2hrQmEwL1M5UEwrRHBFcXJsclBYT0hzekNDdFl5Y1ZBNWo5NUNQRW1VNDFISDhxZllGU0s0VDlnUmFaQXIzaXhTSHFzS3pmUTRiemNzQzlZTGlEK0dTU0VORzJBcnRVbjZSSVNnZVI4U2xMK25xbmlPeGxmL29NYUI3eFRnQ0J1SEtrQUNwcjhpemZxV1ZSNHdMVXgwbXc4Q2hiRzhqL29TVU83cktSRUFLMVZMRXFsN2hPNjRqT243OG90K1RYbnk0ZUg3N2l6Y1QzbitjMnlibzlMTzhySlpzdnNsZFYydm9hTG9DQ3FuQmFNOWFNZnZLbEQ5Z0hDYmFnPT0=";
        String s = CompressUtils.decompressData(x);
        Log.i("decompressData** ",s);
        //System.out.println("haha***"+9/0);
    }

    /*
    小马鲜鲜，循环点击脚本
     */
    @Test
    public void go2(){

        SystemClock.sleep(3000);
        mDevice.click(171,686);
        SystemClock.sleep(1000);
        int i = 0;
        HashSet hset = new HashSet();
        while(i<14000) {
            mDevice.swipe(362,919,366,503,25);
            SystemClock.sleep(300);

            mDevice.click(171,686);

            //检查页面是否完整加载，接口数据是否完整返回
            UiObject2 obj = findObject(By.text("本单还款"));

            //SystemClock.sleep(4000);
            if(obj!=null){
                mDevice.pressKeyCode(KeyEvent.KEYCODE_BACK);
            }else{
                assert false:"脚本停止运行,页面加载超时 15s";
                break;
            }

            SystemClock.sleep(700);
            String orderNo = findObject(By.textStartsWith("票号:")).getText();
            hset.add(orderNo);
            Log.i("i***:",""+i+"--"+orderNo+"--"+hset.size());
            i++;

            boolean b = mDevice.hasObject(By.text("没有更多了"));
            if (true==b){
                mDevice.click(817,1043); //1637
                SystemClock.sleep(1000);
                mDevice.pressKeyCode(KeyEvent.KEYCODE_BACK);
                SystemClock.sleep(1000);
                mDevice.click(817,1637);
                SystemClock.sleep(600);
                return;
            }
        }
    }

    @Test
    public void go3(){
        int i = 0;
        while(i<100){
            i++;
            Log.i("i**:",""+i);
            stepProtect(new ExceptionProtect() {
                @Override
                public void run() {
                    findObject(By.text("竹叶青")).click();
                }
            });

            findObject(By.text("数量")).click();
            findObject(By.text("9")).click();
            findObject(By.text("重量")).click();
            findObject(By.text("3")).click();
            findObject(By.text("单价")).click();
            findObject(By.text("7")).click();
            findObject(By.text("力资非")).click();
            findObject(By.text("6")).click();
            findObject(By.text("确\n定")).click();

        }



    }

    @Test
    public void go4(){
        while(true){
            List<UiObject2> objects = findObjects(By.clazz("android.widget.ScrollView"));
            //System.out.println(objects.size()+"scrollview**");

            UiObject2 object2 = objects.get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0);

            List<UiObject2> children = object2.getChildren();
            //System.out.println(children.size()+"__****__");
            for (UiObject2 obj:children) {
                if(children.size()==6){
                    System.out.print(obj.getText() + "____**");
                }
            }
            System.out.println("\n");
            mDevice.swipe(366,919,366,873,25);
            //SystemClock.sleep(500);
        }
    }

    @Test
    public void goTest() {
        //findObject(By.text("undefined.item_2024-11.day_2024-11-01")).click();
        int i = 1;
        while(i<31) {
            String j = ""+i;
            if(i<10){
                j="0"+i;
            }
            findObject(By.res("undefined.item_2024-11.day_2024-11-" + j)).click();
                                          //undefined.item_2024-11.day_2024-11-10
            i++;
        }
    }

}
