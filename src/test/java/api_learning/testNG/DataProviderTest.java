package api_learning.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "usernameData")
    public void testDataProvider(User user){
        System.out.println(user);
    }

    @DataProvider(name = "usernameData")
    public String[] getUserName(){
        User teo = new User("Teo", 18);
        User ti = new User("Ti", 19);
        User tun = new User("Tun", 20);
        return new String[]{"Teo", "Ti", "Tun"};
    }

    public static class User{
        private String name;
        private int age;

        public User(String name, int age){
            this.name = name;
            this.age = age;
        }

    }
}
