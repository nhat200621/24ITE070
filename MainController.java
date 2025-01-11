package com.example.demo2;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class MainController {
    private PhoneBook phoneBook = new PhoneBook();

    @FXML private Button addButton;
    @FXML private Button birthdayButton;
    @FXML private Button kycButton;
    @FXML private Button accountButton;
    @FXML private Button socialInfoButton;
    @FXML private Button displayButton;
    @FXML private Button searchButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(e -> openAddContact());

        birthdayButton.setOnAction(e -> openBirthdayUpdate());

        kycButton.setOnAction(e -> openKycUpdate());

        accountButton.setOnAction(e -> openAccountUpdate());

        socialInfoButton.setOnAction(e -> openSocialInfoUpdate());

        displayButton.setOnAction(e -> displayContacts());

        searchButton.setOnAction(e -> openSearchContact());

        updateButton.setOnAction(e -> openUpdateContact());

        deleteButton.setOnAction(e -> openDeleteContact());
    }

    private void openAddContact() {
        Stage addStage = new Stage();
        addStage.setTitle("Thêm liên hệ");

        GridPane addGrid = new GridPane();
        addGrid.setVgap(10);
        addGrid.setHgap(10);
        addGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        TextField phoneField = new TextField();
        phoneField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");

        Button submitButton = new Button("Thêm");
        submitButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label nameLabel = new Label("Tên:");
        nameLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);"); // Đổi màu chữ của nhãn

        Label phoneLabel = new Label("Số điện thoại:");
        phoneLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        addGrid.add(nameLabel, 0, 0);
        addGrid.add(nameField, 1, 0);
        addGrid.add(phoneLabel, 0, 1);
        addGrid.add(phoneField, 1, 1);
        addGrid.add(emailLabel, 0, 2);
        addGrid.add(emailField, 1, 2);
        addGrid.add(submitButton, 1, 3);

        submitButton.setOnAction(ev -> {
            phoneBook.addContact(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
            addStage.close();
        });
        Scene addScene = new Scene(addGrid, 300, 200);

        addStage.setScene(addScene);
        addStage.show();
    }

    private void openBirthdayUpdate() {
        Stage birthdayStage = new Stage();
        birthdayStage.setTitle("Cập nhật ngày sinh");

        GridPane birthdayGrid = new GridPane();
        birthdayGrid.setVgap(10);
        birthdayGrid.setHgap(10);
        birthdayGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");
        ChoiceBox<String> contactChoiceBox = new ChoiceBox<>();
        contactChoiceBox.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        for (Contact contact : phoneBook.getContacts()) {
            contactChoiceBox.getItems().add(contact.getName());
        }

        TextField birthdayField = new TextField();
        birthdayField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        birthdayField.setPromptText("yyyy-MM-dd");

        Button updateButton = new Button("Cập nhật");
        updateButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label contactLabel = new Label("Chọn liên hệ:");
        contactLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label birthdayLabel = new Label("Nhập ngày sinh:");
        birthdayLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        birthdayGrid.add(contactLabel, 0, 0);
        birthdayGrid.add(contactChoiceBox, 1, 0);
        birthdayGrid.add(birthdayLabel, 0, 1);
        birthdayGrid.add(birthdayField, 1, 1);
        birthdayGrid.add(updateButton, 1, 2);

        updateButton.setOnAction(e -> {
            String selectedName = contactChoiceBox.getValue();
            String birthdayText = birthdayField.getText();

            try {
                LocalDate birthday = LocalDate.parse(birthdayText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                boolean isUpdated = phoneBook.updateBirthday(selectedName, birthday);

                if (isUpdated) {
                    showAlert("Thành công", "Ngày sinh đã được cập nhật!");
                } else {
                    showAlert("Thất bại", "Không tìm thấy liên hệ với tên: " + selectedName);
                }
            } catch (Exception ex) {
                showAlert("Lỗi", "Ngày sinh không hợp lệ (định dạng: yyyy-MM-dd)");
            }

            birthdayStage.close();
        });

        Scene birthdayScene = new Scene(birthdayGrid, 400, 200);
        birthdayStage.setScene(birthdayScene);
        birthdayStage.show();
    }

    private void showAlert(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label messageLabel = new Label(message);

        messageLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        vbox.getChildren().add(messageLabel);

        vbox.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        Scene alertScene = new Scene(vbox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }

    private void openKycUpdate() {
        Stage kycStage = new Stage();
        kycStage.setTitle("KYC - Cập nhật CCCD");

        GridPane kycGrid = new GridPane();
        kycGrid.setVgap(10);
        kycGrid.setHgap(10);
        kycGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        ChoiceBox<String> contactChoiceBox = new ChoiceBox<>();
        contactChoiceBox.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        for (Contact contact : phoneBook.getContacts()) {
            contactChoiceBox.getItems().add(contact.getName());
        }

        TextField cccdField = new TextField();
        cccdField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        cccdField.setPromptText("Nhập CCCD");

        Button updateButton = new Button("Cập nhật");
        updateButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label contactLabel = new Label("Chọn liên hệ:");
        contactLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label cccdLabel = new Label("CCCD:");
        cccdLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        kycGrid.add(contactLabel, 0, 0);
        kycGrid.add(contactChoiceBox, 1, 0);
        kycGrid.add(cccdLabel, 0, 1);
        kycGrid.add(cccdField, 1, 1);
        kycGrid.add(updateButton, 1, 2);

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        kycGrid.add(resultLabel, 1, 3);

        updateButton.setOnAction(e -> {
            String selectedName = contactChoiceBox.getValue();
            String cccd = cccdField.getText();

            try {
                if (selectedName != null && !cccd.isEmpty()) {
                    boolean isUpdated = phoneBook.updateCccd(selectedName, cccd);

                    if (isUpdated) {
                        showAlert1("Thành công", "CCCD đã được cập nhật!");
                    } else {
                        showAlert1("Thất bại", "Không tìm thấy liên hệ với tên: " + selectedName);
                    }
                } else {
                    showAlert1("Lỗi", "Vui lòng nhập đầy đủ thông tin!");
                }
            } catch (Exception ex) {
                showAlert1("Lỗi", "Có lỗi xảy ra khi cập nhật CCCD.");
            }

            kycStage.close();
        });

        Scene kycScene = new Scene(kycGrid, 400, 200);
        kycStage.setScene(kycScene);
        kycStage.show();
    }

    private void showAlert1(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label messageLabel = new Label(message);

        messageLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        vbox.getChildren().add(messageLabel);

        vbox.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        Scene alertScene = new Scene(vbox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }

    private void openAccountUpdate() {
        Stage accountStage = new Stage();
        accountStage.setTitle("Cập nhật Số tài khoản và Ngân hàng");

        GridPane accountGrid = new GridPane();
        accountGrid.setVgap(10);
        accountGrid.setHgap(10);
        accountGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        ChoiceBox<String> contactChoiceBox = new ChoiceBox<>();
        contactChoiceBox.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        for (Contact contact : phoneBook.getContacts()) {
            contactChoiceBox.getItems().add(contact.getName());
        }

        TextField accountNumberField = new TextField();
        accountNumberField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        accountNumberField.setPromptText("Nhập Số tài khoản");

        TextField bankNameField = new TextField();
        bankNameField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        bankNameField.setPromptText("Nhập Ngân hàng");

        Button updateButton = new Button("Cập nhật");
        updateButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label contactLabel = new Label("Chọn liên hệ:");
        contactLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label accountNumberLabel = new Label("Số tài khoản:");
        accountNumberLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label bankNameLabel = new Label("Ngân hàng:");
        bankNameLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        accountGrid.add(contactLabel, 0, 0);
        accountGrid.add(contactChoiceBox, 1, 0);
        accountGrid.add(accountNumberLabel, 0, 1);
        accountGrid.add(accountNumberField, 1, 1);
        accountGrid.add(bankNameLabel, 0, 2);
        accountGrid.add(bankNameField, 1, 2);
        accountGrid.add(updateButton, 1, 3);

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        accountGrid.add(resultLabel, 1, 4);

        updateButton.setOnAction(e -> {
            String selectedName = contactChoiceBox.getValue();
            String accountNumber = accountNumberField.getText();
            String bankName = bankNameField.getText();

            try {
                if (selectedName != null && !accountNumber.isEmpty() && !bankName.isEmpty()) {
                    boolean isUpdated = phoneBook.updateAccountInfo(selectedName, accountNumber, bankName);

                    if (isUpdated) {
                        showAlert2("Thành công", "Cập nhật Số tài khoản và Ngân hàng thành công!");
                    } else {
                        showAlert2("Thất bại", "Không tìm thấy liên hệ với tên: " + selectedName);
                    }
                } else {
                    showAlert2("Lỗi", "Vui lòng nhập đủ thông tin!");
                }
            } catch (Exception ex) {
                showAlert2("Lỗi", "Có lỗi xảy ra khi cập nhật thông tin tài khoản.");
            }

            accountStage.close();
        });

        Scene accountScene = new Scene(accountGrid, 400, 250);
        accountStage.setScene(accountScene);
        accountStage.show();
    }

    private void showAlert2(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label messageLabel = new Label(message);

        messageLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        vbox.getChildren().add(messageLabel);

        vbox.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        Scene alertScene = new Scene(vbox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }

    private void openSocialInfoUpdate() {
        Stage socialStage = new Stage();
        socialStage.setTitle("Cập nhật Thông tin liên hệ");

        GridPane socialGrid = new GridPane();
        socialGrid.setVgap(10);
        socialGrid.setHgap(10);
        socialGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        ChoiceBox<String> contactChoiceBox = new ChoiceBox<>();
        contactChoiceBox.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        for (Contact contact : phoneBook.getContacts()) {
            contactChoiceBox.getItems().add(contact.getName());
        }

        TextField facebookField = new TextField();
        facebookField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        facebookField.setPromptText("Link Facebook (nếu có)");

        TextField telegramField = new TextField();
        telegramField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        telegramField.setPromptText("Username Telegram (nếu có)");

        TextField threadsField = new TextField();
        threadsField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        threadsField.setPromptText("Link Threads (nếu có)");

        TextField twitterField = new TextField();
        twitterField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        twitterField.setPromptText("Link Twitter (nếu có)");

        TextField zaloField = new TextField();
        zaloField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        zaloField.setPromptText("Link Zalo (nếu có)");

        Button updateButton = new Button("Cập nhật");
        updateButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label contactLabel = new Label("Chọn liên hệ:");
        contactLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label facebookLabel = new Label("Facebook:");
        facebookLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label telegramLabel = new Label("Telegram:");
        telegramLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label threadsLabel = new Label("Threads:");
        threadsLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label twitterLabel = new Label("Twitter:");
        twitterLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label zaloLabel = new Label("Zalo:");
        zaloLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        socialGrid.add(contactLabel, 0, 0);
        socialGrid.add(contactChoiceBox, 1, 0);
        socialGrid.add(facebookLabel, 0, 1);
        socialGrid.add(facebookField, 1, 1);
        socialGrid.add(telegramLabel, 0, 2);
        socialGrid.add(telegramField, 1, 2);
        socialGrid.add(threadsLabel, 0, 3);
        socialGrid.add(threadsField, 1, 3);
        socialGrid.add(twitterLabel, 0, 4);
        socialGrid.add(twitterField, 1, 4);
        socialGrid.add(zaloLabel, 0, 5);
        socialGrid.add(zaloField, 1, 5);
        socialGrid.add(updateButton, 1, 6);

        Label resultLabel = new Label();
        socialGrid.add(resultLabel, 1, 7);

        updateButton.setOnAction(e -> {
            String selectedName = contactChoiceBox.getValue();
            String facebook = facebookField.getText();
            String telegram = telegramField.getText();
            String threads = threadsField.getText();
            String twitter = twitterField.getText();
            String zalo = zaloField.getText();

            if (selectedName != null) {
                boolean isUpdated = phoneBook.updateSocialInfo(selectedName, facebook, telegram, threads, twitter, zalo);
                if (isUpdated) {
                    showAlert3("Thành công", "Cập nhật thông tin liên hệ thành công!");
                } else {
                    showAlert3("Thất bại", "Không tìm thấy liên hệ!");
                }
            } else {
                showAlert3("Lỗi", "Vui lòng chọn liên hệ!");
            }
        });

        Scene socialScene = new Scene(socialGrid, 500, 400);
        socialStage.setScene(socialScene);
        socialStage.show();
    }

    private void showAlert3(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label messageLabel = new Label(message);

        messageLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        vbox.getChildren().add(messageLabel);

        vbox.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        Scene alertScene = new Scene(vbox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }


    private void displayContacts() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Tất cả các liên hệ");

        GridPane displayGrid = new GridPane();
        TextArea displayArea = new TextArea();
        displayArea.setText(phoneBook.displayAllContacts());
        displayArea.setEditable(false);

        displayGrid.add(displayArea, 0, 0);
        displayGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        Scene displayScene = new Scene(displayGrid, 400, 300);
        displayStage.setScene(displayScene);
        displayStage.show();
    }

    private void openSearchContact() {
        Stage searchStage = new Stage();
        searchStage.setTitle("Tìm kiếm liên hệ");

        GridPane searchGrid = new GridPane();
        searchGrid.setVgap(10);
        searchGrid.setHgap(10);
        searchGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        TextField searchField = new TextField();
        searchField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        Button searchButton = new Button("Tìm");
        searchButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Label searchLabel = new Label("Nhập tên hoặc số điện thoại:");
        searchLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        searchGrid.add(searchLabel, 0, 0);
        searchGrid.add(searchField, 1, 0);
        searchGrid.add(searchButton, 1, 1);
        searchGrid.add(resultArea, 0, 2, 2, 1);

        searchButton.setOnAction(e -> {
            String keyword = searchField.getText();
            Contact contact = phoneBook.searchContact(keyword);
            if (contact != null) {
                resultArea.setText("Thông tin liên hệ:\n" + contact.toString());
            } else {
                resultArea.setText("Không tìm thấy liên hệ nào.");
            }
        });

        Scene searchScene = new Scene(searchGrid, 400, 300);
        searchStage.setScene(searchScene);
        searchStage.show();
    }

    private void openUpdateContact() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Cập nhật liên hệ");

        GridPane updateGrid = new GridPane();
        updateGrid.setVgap(10);
        updateGrid.setHgap(10);
        updateGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        TextField oldNameField = new TextField();
        oldNameField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        TextField newNameField = new TextField();
        newNameField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        TextField newPhoneField = new TextField();
        newPhoneField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        TextField newEmailField = new TextField();
        newEmailField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");

        Button updateButton = new Button("Cập nhật");
        updateButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Label oldNameLabel = new Label("Tên cũ:");
        oldNameLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label newNameLabel = new Label("Tên mới:");
        newNameLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label newPhoneLabel = new Label("Số điện thoại mới:");
        newPhoneLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        Label newEmailLabel = new Label("Email mới:");
        newEmailLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        updateGrid.add(oldNameLabel, 0, 0);
        updateGrid.add(oldNameField, 1, 0);
        updateGrid.add(newNameLabel, 0, 1);
        updateGrid.add(newNameField, 1, 1);
        updateGrid.add(newPhoneLabel, 0, 2);
        updateGrid.add(newPhoneField, 1, 2);
        updateGrid.add(newEmailLabel, 0, 3);
        updateGrid.add(newEmailField, 1, 3);
        updateGrid.add(updateButton, 1, 4);
        updateGrid.add(resultArea, 0, 5, 2, 1);

        updateButton.setOnAction(e -> {
            String oldName = oldNameField.getText();
            String newName = newNameField.getText();
            String newPhone = newPhoneField.getText();
            String newEmail = newEmailField.getText();

            boolean isUpdated = phoneBook.updateContact(oldName, newName, newPhone, newEmail);

            if (isUpdated) {
                resultArea.setText("Cập nhật liên hệ thành công!");
            } else {
                resultArea.setText("Không tìm thấy liên hệ với tên: " + oldName);
            }
        });

        Scene updateScene = new Scene(updateGrid, 400, 300);
        updateStage.setScene(updateScene);
        updateStage.show();
    }

    private void openDeleteContact() {
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Xóa liên hệ");

        GridPane deleteGrid = new GridPane();
        deleteGrid.setVgap(10);
        deleteGrid.setHgap(10);
        deleteGrid.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33)");

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: linear-gradient(to bottom, #FFDEE9, #B5FFFC);");
        Button deleteButton = new Button("Xóa");
        deleteButton.setStyle("-fx-background-color: Blue; -fx-text-fill: Yellow;");

        Label nameLabel = new Label("Nhập tên liên hệ cần xóa:");
        nameLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");

        deleteGrid.add(nameLabel, 0, 0);
        deleteGrid.add(nameField, 1, 0);
        deleteGrid.add(deleteButton, 1, 1);

        deleteButton.setOnAction(e -> {
            String nameToDelete = nameField.getText();

            if (nameToDelete.isEmpty()) {
                showAlert4("Lỗi", "Vui lòng nhập tên liên hệ cần xóa.");
                return;
            }

            boolean isDeleted = phoneBook.deleteContact(nameToDelete);

            if (isDeleted) {
                showAlert4("Thành công", "Liên hệ đã được xóa thành công!");
            } else {
                showAlert4("Thất bại", "Không tìm thấy liên hệ với tên: " + nameToDelete);
            }

            deleteStage.close();
        });

        Scene deleteScene = new Scene(deleteGrid, 300, 200);
        deleteStage.setScene(deleteScene);
        deleteStage.show();
    }

    private void showAlert4(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: linear-gradient(to bottom, #2E3192, #1BFFFF);");
        vbox.getChildren().add(messageLabel);

        vbox.setStyle("-fx-background-color: linear-gradient(to right, #FF7E5F, #FEB47B, #FFCC33);");

        Scene alertScene = new Scene(vbox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }
}




