import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Ana sınıf, JavaFX uygulamasını başlatır.
public class Main extends Application {

    // ViewModel nesnesi oluşturulur.
    private final TurDetayViewModel viewModel = new TurDetayViewModel();

    // Uygulama başladığında çağrılan metod.
    @Override
    public void start(Stage primaryStage) {
        // TableView oluşturulur ve ViewModel'den gelen tur listesiyle doldurulur.
        TableView<TurDetayModel> tableView = new TableView<>();
        tableView.setItems(viewModel.getTurList());

        // Sütunlar oluşturulur ve TurDetayModel sınıfındaki alanlarla ilişkilendirilir.
        TableColumn<TurDetayModel, String> startCol = new TableColumn<>("Start Destination");
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDestination"));

        TableColumn<TurDetayModel, String> endCol = new TableColumn<>("End Destination");
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDestination"));

        TableColumn<TurDetayModel, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<TurDetayModel, String> currencyCol = new TableColumn<>("Currency");
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));

        // TableView'e sütunlar eklenir.
        tableView.getColumns().addAll(startCol, endCol, priceCol, currencyCol);

        // Yeni tur detayı girişi için kontrol bileşeni oluşturulur.
        TurDetayInputControl inputControl = new TurDetayInputControl();
        inputControl.setStartCityOptions(new ComboBox<>(viewModel.getCities()));
        inputControl.setEndCityOptions(new ComboBox<>(viewModel.getCities()));

        // "Add" düğmesine tıklama işlemi tanımlanır.
        inputControl.getAddButton().setOnAction(e -> {
            // Kullanıcı tarafından girilen veriler alınır.
            String startCity = inputControl.getStartCityComboBox().getValue();
            String endCity = inputControl.getEndCityComboBox().getValue();
            double price = Double.parseDouble(inputControl.getPriceTextField().getText());

            // ViewModel aracılığıyla yeni tur detayı eklenir.
            viewModel.addTurDetay(startCity, endCity, price, "TRY");

            // Giriş kontrolü temizlenir.
            inputControl.getStartCityComboBox().getSelectionModel().clearSelection();
            inputControl.getEndCityComboBox().getSelectionModel().clearSelection();
            inputControl.getPriceTextField().clear();
        });

        // Arayüz düzeni oluşturulur ve bileşenler yerleştirilir.
        BorderPane root = new BorderPane();
        root.setTop(inputControl); // Giriş kontrolü üst kısıma yerleştirilir.
        root.setCenter(tableView); // TableView merkeze yerleştirilir.

        // Pencereye sahne oluşturulur ve ayarlanır.
        Scene scene = new Scene(root, 800, 600);

        // Pencere başlığı ayarlanır ve sahne gösterilir.
        primaryStage.setTitle("Tur Detay View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Ana metot, uygulamayı başlatır.
    public static void main(String[] args) {
        launch(args);
    }
}
