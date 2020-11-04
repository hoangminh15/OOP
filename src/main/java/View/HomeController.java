package View;

import Model.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    DatePicker thoiGian;
    @FXML
    TextField sanText;
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
    TextArea thongTinText;

    String dateData;
    DAO DAOObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DAOObject = new DAO();
    }

    public boolean checkEmpty(){
        if(sanText.getText() == "" || maText.getText() == ""){
            Alert missingFieldAlert = new Alert(Alert.AlertType.INFORMATION);
            missingFieldAlert.setTitle("Missing Field");
            missingFieldAlert.setContentText("You need to fill in all the required field");
            return false;
        }
        return true;
    }

    //Action Listener cho DatePicker
    public void pickDate(ActionEvent event){
        LocalDate localDate = thoiGian.getValue();
        String day = String.valueOf(localDate.getDayOfMonth());
        String month = String.valueOf(localDate.getMonthValue());
        String year = String.valueOf(localDate.getYear());
        dateData = year.concat(month).concat(day);
        //Can set default date
    }

    //Listener cho xemTheoMaButton
    public void xemTheoMa(ActionEvent event){
//        if(checkEmpty()) return;
//        if(!(sanText.getText() == "") || !(maText.getText() == "")){
//            Alert missingFieldAlert = new Alert(Alert.AlertType.INFORMATION);
//            missingFieldAlert.setTitle("Missing Field");
//            missingFieldAlert.setContentText("You need to fill in all the required field");
//            missingFieldAlert.show();
//            return;
//        }
        String maCoPhieu = maText.getText();
        String maSan = sanText.getText();
        //Get data
        DAOObject.layDataTheoMa(dateData, maSan, maCoPhieu);
        //Sinh cau
    }

    //Listener cho Bluechip
    public void xemBluechip(ActionEvent event){
        //Get data
        //Sinh cau

    }

    //Listener cho nuocNgoai
    public void xemNuocNgoai(ActionEvent event){
        //Get Data
        //Sinh cau

    }

    public void xemTangManh(ActionEvent event){
        //Get Data
        //Sinh cau
    }

    public void xemGiamManh(ActionEvent event){
        //Get Data
        //Sinh cau
    }

    public void xemNhomCoPhieu(ActionEvent event){
        //Get data
        //Sinh cau
    }



}
