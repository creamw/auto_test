package tool;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import config.RedisConfig;
import redis.clients.jedis.Jedis;

public class DAO {

    public Jedis getJedis(){
        //P
//        RedisConfig redisConfig = new RedisConfig("47.103.62.98",6379,"cxmPre*20@0");
//        Jedis jedis = new Jedis(redisConfig.getIp(),redisConfig.getPort());
//        jedis.auth(redisConfig.getAuth());

        //T
        RedisConfig redisConfig = new RedisConfig("47.103.135.9",6379,"cxmTest20@0!");
        Jedis jedis = new Jedis(redisConfig.getIp(),redisConfig.getPort());
        jedis.auth(redisConfig.getAuth());


        return jedis;
    }



    public String getSmsCode(String phone) {
        Jedis jedis =  getJedis();
        jedis.select(2);
        String smsCode = "";
        long l = System.currentTimeMillis();
        while (true){
            smsCode = jedis.get("smsCode:"+phone+"7");
            if(smsCode!=null||System.currentTimeMillis()-l>7000){
                break;
            }
        }

        JSONObject parse = JSONObject.parseObject(smsCode);
        Object code = parse.getString("code");
        String s = code.toString();
        System.out.println("smsCode:"+s);//输出获取的值
        jedis.close();
        return s;
    }

    public String getToken(String personId){
        Jedis jedis =  getJedis();
        jedis.select(2);
        String loginInfo = "";
        long l = System.currentTimeMillis();
        while (true){
            loginInfo = jedis.get("cxmLoginCacheValue_::com.cxm.core.login.user.INTEGRATIONAPP."+personId);
            if(loginInfo!=null||System.currentTimeMillis()-l>7000){
                break;
            }
        }

        //loginInfo=loginInfo.substring(1,loginInfo.length()-1);
        Log.i("token**",loginInfo);
        JSONArray parse = JSONArray.parseArray(loginInfo);

        JSONObject jsonObject = parse.getJSONObject(1);
        String accessToken = jsonObject.getString("accessToken");
        Log.i("token**",accessToken);
        //Object code = parse.getString("code");
        //String s = code.toString();
        //System.out.println("smsCode:"+s);//输出获取的值
        jedis.close();
        return "";
    }




    @Test
    public void removeKey(String phone){
        Jedis jedis =  getJedis();
        jedis.select(2);
        jedis.del("smsCode:"+phone+"7");
        jedis.close();
    }

}
