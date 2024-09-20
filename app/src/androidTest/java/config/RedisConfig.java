package config;

public class RedisConfig {
    private String ip;

    private Integer port;

    private String auth;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public RedisConfig(String ip, Integer port, String auth) {
        this.ip = ip;
        this.port = port;
        this.auth = auth;
    }
}
