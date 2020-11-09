package View;

import Entity.DataTheoMa;
import Helper.DateValidator;
import DAO.DatabaseGetter;
import Sentence.TestSentence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    DatePicker thoiGian;
    @FXML
    ComboBox<String> sanText;
    @FXML
    TextField maText;
    @FXML
    Button xemTheoMaButton;
    @FXML
    Button bluechipButton;
    @FXML
    Button nuocNgoaiButton;
    @FXML
    Button tangManhButton;
    @FXML
    Button giamManhButton;
    @FXML
    ComboBox nhomCoPhieu;
    @FXML
    Button xemTheoNhomButton;
    @FXML
    TextArea banTinText;
    @FXML
    ImageView imageView;


    String dateData;
    DatabaseGetter databaseGetterObject;
    DateValidator dateValidator;
    TestSentence testSentence;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseGetterObject = new DatabaseGetter();
        dateValidator = new DateValidator();
        File file = new File("View/stockPic.jpeg");
        Image image = new Image(file.toURI().toString());
        imageView = new ImageView(image);
        ObservableList<String> comboboxList = FXCollections.observableArrayList("HSX", "HNX");
        sanText.setItems(comboboxList);
    }

    //Action Listener cho DatePicker
    public void pickDate(ActionEvent event) {
        LocalDate localDate = thoiGian.getValue();
        //Kiểm tra ngày liệu có phải thứ 7 + CN
        //0 tương đương với Sunday, 6 tương đương với Saturday là 2 ngày thị trường chứng khoán không hoạt động
        int dayOfWeekByInt = dateValidator.findDayOfWeek(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        System.out.println(dayOfWeekByInt);
        if (dayOfWeekByInt == 0 || dayOfWeekByInt == 6) {
            Alert invalidDay = new Alert(Alert.AlertType.INFORMATION);
            invalidDay.setTitle("Invalid day!");
            invalidDay.setHeaderText("Stock market isn't active on Saturday and Sunday");
            invalidDay.setContentText("Please choose another day...!");
            ButtonType OKbuttonType = new ButtonType("Got it!", ButtonBar.ButtonData.OK_DONE);
            invalidDay.getButtonTypes().setAll(OKbuttonType, ButtonType.CANCEL);
            invalidDay.show();
            thoiGian.setValue(null);
            System.out.println("Here");
            return;
        }
        String day = String.valueOf(localDate.getDayOfMonth());
        String month = String.valueOf(localDate.getMonthValue());
        String year = String.valueOf(localDate.getYear());
        dateData = year.concat(month).concat(day);
    }

    //Listener cho xemTheoMaButton
    public void xemTheoMa(ActionEvent event) throws Exception {
        if ((sanText.getValue().equals("")) || (maText.getText().equals("")) || (dateData.isBlank())) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.INFORMATION);
            missingFieldAlert.setTitle("Missing Field");
            missingFieldAlert.setContentText("You need to fill in all the required field");
            missingFieldAlert.show();
            return;
        }
        String maCoPhieu = maText.getText().toUpperCase();
        String maSan = sanText.getValue().toUpperCase();
        //Get data
        DataTheoMa data = databaseGetterObject.layDataTheoMa(dateData, maSan, maCoPhieu);
        //Sinh cau
        testSentence = new TestSentence(data);
        String testResult = testSentence.generateSentence();
        banTinText.setText(testResult);

    }

    //Listener cho Bluechip
    public void xemBluechip(ActionEvent event) throws Exception {
        //Get data
        //Tra ve list cac "data object" thuoc nhom co phieu BlueChip
        ArrayList<DataTheoMa> listBluechip = databaseGetterObject.layDataBluechip(dateData);
        //Test: in ten cac co phieu bluechip
        System.out.println("Here!");
        for (DataTheoMa bluechip : listBluechip) {
            banTinText.setText(bluechip.getMaCoPhieu());
        }
        //Sinh cau
    }

    //Listener cho nuocNgoai
    public void xemNuocNgoai(ActionEvent event) {
        //Get Data
        //Sinh cau
    }

    public void xemTangManh(ActionEvent event) {
        //Get Data
        //Sinh cau

    }

    public void xemGiamManh(ActionEvent event) {
        //Get Data
        //Sinh cau
    }

    public void xemNhomCoPhieu(ActionEvent event) {
        //Get data
        //Sinh cau
    }


}
