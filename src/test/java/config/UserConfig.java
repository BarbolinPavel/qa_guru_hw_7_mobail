package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:user.properties"
})
public interface UserConfig extends Config{
    @Config.Key("browserstack.user")
    String getBrowserstackUser();

    @Config.Key("browserstack.key")
    String getBrowserstackKey();
}
