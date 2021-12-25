package test_data.authentication;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static LoginCred[] buildLoginCreds(String filePath) {
        LoginCred[] loginCreds = new LoginCred[]{};
        String absoluteFilePath = System.getProperty("user.dir") + filePath;
        try(
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
                ) {
            // Create
            Gson gson = new Gson();
            loginCreds = gson.fromJson(reader, LoginCred[].class);

            for(LoginCred loginCred: loginCreds) {
                System.out.println(loginCred.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginCreds;
    }

    public static void main(String[] args) {
        buildLoginCreds("/src/main/resources/test-data/valid-login-credential.json");
    }
}
