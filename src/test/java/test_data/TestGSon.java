package test_data;

import com.google.gson.Gson;
import test_data.computer.ComputerData;

import java.util.Arrays;

public class TestGSon {

    // Convert from JSON to Java Object
    // COnvert Java Object to GSON

    public static void main(String[] args) {
//        String JSonString = "   {\n" +
//                "    \"processorType\": \"Fast\",\n" +
//                "    \"ram\": \"8 GB\",\n" +
//                "    \"hdd\": \"320 GB\",\n" +
//                "    \"software\": \"Image Viever\"\n" +
//                "  }";
//        exploreGsonFeature();
//        Gson gson = new Gson();
//        ComputerData computerData = gson.fromJson(JSonString, ComputerData.class);
//        System.out.println(computerData);
//
//        System.out.println(gson.toJson(computerData));
        testDataBuilder();

    }

    private static void testDataBuilder() {
        String fileLocation = "\\src\\test\\java\\test\\computer\\CheapComputerDataList.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);

        // dua vao loai nao thi tra ve loai do, flexible
//        String fileLocation = "\\src\\test\\java\\test\\computer\\CheapComputerData.json";
//        ComputerData computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData.class);
        System.out.println(computerData);

    }

    private static void exploreGsonFeature() {
    }

}
