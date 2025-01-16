package io.github.mung.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.github.mung.constants.GlobalVars;
import io.github.mung.helpers.SystemHelpers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {

    //Jackson
    private static Map<String, String> CONFIGMAP;

    //Json Path
    private static BufferedReader bufferedReader;
    private static StringBuffer stringBuffer;
    private static DocumentContext jsonContext;
    private static String lines;
    private static String jsonFilePathDefault = SystemHelpers.getCurrentDir() + "src/test/resources/datajson/store.json";

    private JsonUtils() {
        super();
    }

    //Được sử dụng để khởi tạo thành viên dữ liệu static. (CONFIGMAP)
    //Nó được thực thi trước phương thức main tại lúc khởi tạo lớp này.
    static {
        try {
            CONFIGMAP = new ObjectMapper().readValue(new File(SystemHelpers.getCurrentDir() + GlobalVars.JSON_DATA_FILE_PATH), new TypeReference<HashMap<String, String>>() {
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toLowerCase()))) {
            try {
                throw new Exception("Key name " + key + " is not found. Please check config.json");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return CONFIGMAP.get(key.toLowerCase());
    }

    public static StringBuffer readJsonFile(String jsonPath) {
        try {
            bufferedReader = new BufferedReader(new FileReader(SystemHelpers.getCurrentDir() + jsonPath));
            stringBuffer = new StringBuffer();
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public static String getJson(String filePath, String key){
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return (String) jsonObject.get(key);
    }

    public static void setJsonFile(String jsonPath) {
        try {
            bufferedReader = new BufferedReader(new FileReader(SystemHelpers.getCurrentDir() + jsonPath));
            stringBuffer = new StringBuffer();
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines);
            }
            jsonContext = JsonPath.parse(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJsonDataSourceString() {
        if (stringBuffer == null) {
            try {
                bufferedReader = new BufferedReader(new FileReader(jsonFilePathDefault));
                stringBuffer = new StringBuffer();
                while ((lines = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lines);
                }
                jsonContext = JsonPath.parse(stringBuffer.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public static Object getData(String key) {
        if (jsonContext == null) {
            getJsonDataSourceString();
        }
        //JsonPath.read(getJsonDataSourceString(), key);
        return jsonContext.read(key);
    }
}
