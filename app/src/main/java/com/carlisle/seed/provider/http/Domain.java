package com.carlisle.seed.provider.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by carlisle on 11/18/16.
 */

public class Domain {

    static private Map<String, String> DOMAIN = new HashMap<String, String>() {{
        put(DomainType.GITHUB.type, "https://api.github.com");
    }};

    public static String get(DomainType domainType) {
        return DOMAIN.get(domainType.type);
    }

    public enum DomainType {
        GITHUB("github");

        public String type;

        DomainType(String type) {
            this.type = type;
        }
    }
}
