package com.hoping.owl.flymock;

import com.hoping.owl.flymock.cache.LocalCache;
import com.hoping.owl.flymock.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by houping wang on 2019/3/15
 *
 * @author houping wang
 */
public class AddressDict {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDict.class);

    private static final LocalCache<AddressDict> LOCAL_CACHE = new LocalCache<>();

    private static final long EXPIRE_MINUTE = 180;

    private HashMap<String, String> hashMap = null;

    private List<String> provinces = new ArrayList<>(64);

    private List<String> citys = new ArrayList<>(256);

    private List<String> countys = new ArrayList<>(4096);

    private AddressDict(String addressFile) {
        this.hashMap = new LinkedHashMap<>();
        init(addressFile);
    }

    private AddressDict() {
        this("address_dict");
    }

    public static String province() {
        AddressDict addressDict = getAddressDict();
        return addressDict.getValue(ArrayUtil.pick(addressDict.provinces));
    }

    public static String city() {
        return city(false);
    }

    public static String city(boolean bl) {
        AddressDict addressDict = getAddressDict();
        String key = ArrayUtil.pick(addressDict.citys);
        if (key == null) {
            throw new FlyMockException("county key is null");
        }
        String province = addressDict.getValue(key.substring(0, 2) + "0000");
        String city = addressDict.getValue(key);
        return bl ? province + " " + city : city;
    }

    public static String county() {
        return county(false);
    }

    public static String county(boolean bl) {
        AddressDict addressDict = getAddressDict();
        String key = ArrayUtil.pick(addressDict.countys);
        if (key == null) {
            throw new FlyMockException("county key is null");
        }
        String province = addressDict.getValue(key.substring(0, 2) + "0000");
        String city = addressDict.getValue(key.substring(0, 4) + "00");
        String county = addressDict.getValue(key);
        return bl ? province + " " + city + " " + county : county;
    }

    public static String getRandomKey() {
        AddressDict addressDict = getAddressDict();
        String[] keys = addressDict.hashMap.keySet().toArray(new String[0]);
        return keys[new Random().nextInt(keys.length - 1)];
    }

    private static AddressDict getAddressDict() {
        AddressDict cacheAddressDict = LOCAL_CACHE.get();
        if (null != cacheAddressDict) {
            return cacheAddressDict;
        }
        synchronized (AddressDict.class) {
            AddressDict addressDict = LOCAL_CACHE.get();
            if (null != addressDict) {
                return addressDict;
            }
            AddressDict addressDictNew = new AddressDict();
            LOCAL_CACHE.put(addressDictNew, EXPIRE_MINUTE);
            return addressDictNew;
        }
    }

    public String getValue(String key) {
        String value = hashMap.get(key);
        return value == null ? "" : value;
    }

    private void init(String addressFile) {
        InputStream inputStream = AddressDict.class.getClassLoader().getResourceAsStream(addressFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String readLine = "";
            while ((readLine = bufferedReader.readLine()) != null) {
                String[] address = readLine.split(",");
                hashMap.put(address[0], address[1]);
                if (address[0].endsWith("0000")) {
                    provinces.add(address[0]);
                } else if (address[0].endsWith("00")) {
                    citys.add(address[0]);
                } else {
                    countys.add(address[0]);
                }
            }
        } catch (IOException e) {
            throw new FlyMockException(e.getMessage(), e);
        }
    }
}
