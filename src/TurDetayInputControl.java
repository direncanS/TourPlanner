import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

// TurDetayInputControl sınıfı, tur detayları girişi için kullanılan kontrol bileşenini oluşturur. Reusable olması adına oluşturulmuştur.
public class TurDetayInputControl extends HBox {
    private final ComboBox<String> startCityComboBox; // Başlangıç şehri seçim kutusu
    private final ComboBox<String> endCityComboBox; // Bitiş şehri seçim kutusu
    private final TextField priceTextField; // Fiyat giriş alanı
    private final Button addButton; // Yeni tur detayı eklemek için düğme

    // Kurucu metot, bileşenleri oluşturur ve düzenler.
    public TurDetayInputControl() {
        super(10); // Elemanlar arası boşluk

        startCityComboBox = new ComboBox<>(); // Başlangıç şehri için ComboBox oluşturulur
        endCityComboBox = new ComboBox<>(); // Bitiş şehri için ComboBox oluşturulur
        priceTextField = new TextField(); // Fiyat girişi için TextField oluşturulur
        priceTextField.setPromptText("Enter Price"); // Fiyat girişi için varsayılan metin ayarlanır

        addButton = new Button("Add"); // "Add" düğmesi oluşturulur

        // Bileşenler HBox'a eklenir
        getChildren().addAll(
                new Label("Start City:"), startCityComboBox, // Başlangıç şehri için etiket ve ComboBox eklenir
                new Label("End City:"), endCityComboBox, // Bitiş şehri için etiket ve ComboBox eklenir
                new Label("Price:"), priceTextField, // Fiyat için etiket ve TextField eklenir
                addButton // "Add" düğmesi eklenir
        );

        setPadding(new Insets(10)); // HBox kenar boşluğu ayarlanır
    }

    // Başlangıç şehri seçeneklerini ayarlar
    public void setStartCityOptions(ComboBox<String> options) {
        startCityComboBox.getItems().setAll(options.getItems());
    }

    // Bitiş şehri seçeneklerini ayarlar
    public void setEndCityOptions(ComboBox<String> options) {
        endCityComboBox.getItems().setAll(options.getItems());
    }

    // Başlangıç şehri ComboBox'ını döndürür
    public ComboBox<String> getStartCityComboBox() {
        return startCityComboBox;
    }

    // Bitiş şehri ComboBox'ını döndürür
    public ComboBox<String> getEndCityComboBox() {
        return endCityComboBox;
    }

    // Fiyat TextField'ını döndürür
    public TextField getPriceTextField() {
        return priceTextField;
    }

    // "Add" düğmesini döndürür
    public Button getAddButton() {
        return addButton;
    }
}
