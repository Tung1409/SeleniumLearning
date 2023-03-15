package models.components;

import java.lang.reflect.Constructor;

public class ComponentExploring {

    public <T extends LoginPage> void login(Class<T> loginPageClass){

        Class<?>[] parameters = new Class[]{};
        /* muon tao ra obj thi phai tao Constructor moi tao ra duoc obj
        * dau tien phai xac dinh duoc loai du lieu ma Constructor nhan
        * 2. toi can 1 Constructor type T
        * 3. Constructor lay tu khuon loginPageClass
        * 4. dua parameters vao
        * handle try catch
        * 5. tao. obj tu Constructor
        * obj tra ve loai T
        * loai T extends Loginpage nen co the .login()
         */

        try {
            Constructor<T> constructor = loginPageClass.getConstructor(parameters);
            T loginpageObj = constructor.newInstance();
            loginpageObj.login();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ComponentExploring().login(InternalLoginPage.class);
        new ComponentExploring().login(ExternalLoginPage.class);
    }
}


