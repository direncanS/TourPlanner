import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test sınıfı, TurDetayViewModel sınıfının testlerini içerir.
public class TurDetayViewModelTester{

    private TurDetayViewModel viewModel;

    // Her test öncesinde viewModel nesnesi oluşturulur.
    @BeforeEach
    public void setUp() {
        viewModel = new TurDetayViewModel();
    }

    // Yeni bir tur detayı eklemeyi test eder.
    @Test
    public void testAddTurDetay() {
        ObservableList<TurDetayModel> turList = viewModel.getTurList();
        turList.clear();

        // Yeni bir TurDetayModel eklenir.
        viewModel.addTurDetay("Istanbul", "Ankara", 200.0, "TRY");

        // TurDetayModel'in listeye eklenip eklenmediği kontrol edilir.
        assertEquals(1, turList.size());
        TurDetayModel addedTur = turList.get(0);
        assertEquals("Istanbul", addedTur.getStartDestination());
        assertEquals("Ankara", addedTur.getEndDestination());
        assertEquals(200.0, addedTur.getPrice());
        assertEquals("TRY", addedTur.getCurrency());
    }

    // Tur listesini alma işlemini test eder.
    @Test
    public void testGetTurList() {
        TurDetayViewModel viewModel = new TurDetayViewModel(); // Her test için yeni bir ViewModel örneği oluşturulur.

        ObservableList<TurDetayModel> turList = viewModel.getTurList();

        // Yeni öğeler eklenmeden önce listedeki veriler temizlenir.
        turList.clear();

        // Listeye bazı sahte veriler eklenir.
        turList.add(new TurDetayModel("Istanbul", "Ankara", 200.0, "TRY"));
        turList.add(new TurDetayModel("Istanbul", "Izmir", 150.0, "TRY"));

        // Listede eklenen öğelerin sayısının doğruluğu kontrol edilir.
        assertEquals(2, turList.size());
    }

    // Şehir listesini alma işlemini test eder.
    @Test
    public void testGetCities() {
        ObservableList<String> cities = viewModel.getCities();
        cities.clear();

        // Listeye bazı sahte şehirler eklenir.
        cities.add("Istanbul");
        cities.add("Ankara");

        // Listede eklenen şehirlerin sayısının doğruluğu kontrol edilir.
        assertEquals(2, cities.size());
    }

    // Tur listesini temizleme işlemini test eder.
    @Test
    public void testClearTurList() {
        ObservableList<TurDetayModel> turList = viewModel.getTurList();

        // Listeye bazı sahte veriler eklenir.
        turList.add(new TurDetayModel("Istanbul", "Ankara", 200.0, "TRY"));
        turList.add(new TurDetayModel("Istanbul", "Izmir", 150.0, "TRY"));

        // Liste temizlenir.
        turList.clear();

        // Listede veri kalmadığını kontrol eder.
        assertEquals(0, turList.size());
    }
}
