package test.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;

public class DataProviderComputerDataTest {

    @Test(dataProvider = "computerData")
    public void testDataProvider(ComputerData computerData) {
        System.out.println(computerData);
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\test\\java\\test\\computer\\CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }


}
