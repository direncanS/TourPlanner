import javafx.beans.property.*;
import javafx.collections.*;

// TurDetayViewModel sınıfı, tur detaylarını ve şehir listesini yönetir.
public class TurDetayViewModel {
    private final ObservableList<TurDetayModel> turList = FXCollections.observableArrayList(); // Tur detayları listesi
    private final ObservableList<String> cities = FXCollections.observableArrayList( // Şehir listesi
            // Türkiye'nin bazı şehirlerini içeren varsayılan bir liste
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya",
            "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur",
            "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne",
            "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane",
            "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu",
            "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu",
            "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon",
            "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt",
            "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova",
            "Karabük", "Kilis", "Osmaniye", "Düzce"
    );

    // Constructor
    public TurDetayViewModel() {
        // Gösterim amaçlı bazı verilerin başlangıç değerleri atanır
        turList.add(new TurDetayModel("Istanbul", "Ankara", 200.0, "TRY"));
        turList.add(new TurDetayModel("Istanbul", "Izmir", 150.0, "TRY"));
        turList.add(new TurDetayModel("Ankara", "Antalya", 180.0, "TRY"));
    }

    // Tur detayları listesini döndürür
    public ObservableList<TurDetayModel> getTurList() {
        return turList;
    }

    // Şehir listesini döndürür
    public ObservableList<String> getCities() {
        return cities;
    }

    // Yeni bir tur detayı ekler
    public void addTurDetay(String startDestination, String endDestination, double price, String currency) {
        turList.add(new TurDetayModel(startDestination, endDestination, price, currency));
    }
}
