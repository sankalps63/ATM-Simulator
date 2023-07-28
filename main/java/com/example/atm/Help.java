package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Help {
    private Stage stage;
    private UserInfo userinfo = new UserInfo();
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Text text;

    @FXML
    public void initialize(){
        text.setText("1. Account Management:\n\n" +
                "- Opening an Account: To open a new account, visit any of our branches with the required documents (ID proof, address proof, etc.), or apply online through our secure website. Once your application is approved, you will receive your account details via email.\n\n" +
                "- Account Balance: To check your account balance, log in to the application using your credentials, and you will see your account balance on the dashboard.\n\n" +
                "- Updating Contact Information: If you need to update your contact information (phone number, address, email), you can do so from the settings menu within the application.\n\n" +
                "\n" +
                "2. Transactions:\n\n" +
                "- Deposits: To make a deposit, use our ATM services or visit any of our branches during business hours. You can also transfer funds from another account you hold within our bank or use online banking services.\n\n" +
                "- Withdrawals: Withdraw cash from our ATMs using your ATM card. You can also withdraw funds at the branch counter or make electronic fund transfers through online banking.\n\n" +
                "- Fund Transfers: Easily transfer funds between your accounts or to another bank account within the country through our secure online banking portal.\n\n" +
                "\n" +
                "3. Online Banking:\n\n" +
                "- Enrolling in Online Banking: To enroll in online banking, visit our website and click on the \"Register\" option. Follow the instructions to set up your online banking account.\n\n" +
                "- Forgot Password: If you forget your password, click on the \"Forgot Password\" link on the login page. You will receive an email with instructions to reset your password securely.\n\n" +
                "- Security Tips: We take your security seriously. Never share your password or OTP (One-Time Password) with anyone. Our customer support will never ask for this information.\n\n" +
                "\n" +
                "4. Customer Support:\n\n" +
                "- Contact Us: If you have any queries or face any issues, feel free to contact our customer support team at [Customer Support Phone Number] or email us at [Customer Support Email]. We are available 24/7 to assist you.\n\n" +
                "- Fraud Awareness: Be cautious of phishing emails, suspicious phone calls, or messages claiming to be from the bank. We will never ask for sensitive information via email or call.\n\n" +
                "\n" +
                "5. Privacy and Data Security:\n\n" +
                "- Privacy Policy: For details about how we handle your data, please review our privacy policy available on our website.\n\n" +
                "- Secure Transactions: All transactions within our banking application are encrypted and secured using the latest technologies.\n\n" +
                "\n\n" +
                "We hope this help page provides valuable information for your banking needs. If you have any further questions, don't hesitate to reach out to our support team. Thank you for choosing our banking services!");
    }
    @FXML
    public void gtDash() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Dash cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }
}
