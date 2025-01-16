package io.github.mung.helpers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonHelpers {

    //Json Path
    private BufferedReader bufferedReader;
    private StringBuffer stringBuffer;
    private DocumentContext jsonContext;
    private String lines;
    private String jsonFilePathDefault = SystemHelpers.getCurrentDir() + "src/test/resources/datajson/store.json";

    public void setJsonFile(String jsonPath) {

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

  public static String getJson(String filePath, String key) {
      JSONObject jsonObject = null;

      try {
          jsonObject = (JSONObject ) new JSONParser().parse(new FileReader(filePath));
      }  catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
        return (String) jsonObject.get(key);
  }

    public Object getData(String key) {
        //JsonPath.read(getJsonDataSourceString(), key);
        return jsonContext.read(key);
    }
}
