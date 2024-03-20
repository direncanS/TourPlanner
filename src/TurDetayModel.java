import javafx.beans.property.*;

// TurDetayModel sınıfı, bir tur detayını temsil eder.
public class TurDetayModel {
    // Tur detayının başlangıç şehri
    private final StringProperty startDestination = new SimpleStringProperty();
    // Tur detayının bitiş şehri
    private final StringProperty endDestination = new SimpleStringProperty();
    // Tur detayının fiyatı
    private final DoubleProperty price = new SimpleDoubleProperty();
    // Tur detayının para birimi
    private final StringProperty currency = new SimpleStringProperty();

    // Kurucu metod, tur detayı özelliklerini başlatır.
    public TurDetayModel(String startDestination, String endDestination, double price, String currency) {
        this.startDestination.set(startDestination); // Başlangıç şehri ayarlanır
        this.endDestination.set(endDestination); // Bitiş şehri ayarlanır
        this.price.set(price); // Fiyat ayarlanır
        this.currency.set(currency); // Para birimi ayarlanır
    }

    // Başlangıç şehri özelliğinin değerini döndürür
    public String getStartDestination() {
        return startDestination.get();
    }

    // Başlangıç şehri özelliğini ayarlar
    public void setStartDestination(String startDestination) {
        this.startDestination.set(startDestination);
    }

    // Başlangıç şehri özelliğinin StringProperty'sini döndürür
    public StringProperty startDestinationProperty() {
        return startDestination;
    }

    // Bitiş şehri özelliğinin değerini döndürür
    public String getEndDestination() {
        return endDestination.get();
    }

    // Bitiş şehri özelliğini ayarlar
    public void setEndDestination(String endDestination) {
        this.endDestination.set(endDestination);
    }

    // Bitiş şehri özelliğinin StringProperty'sini döndürür
    public StringProperty endDestinationProperty() {
        return endDestination;
    }

    // Fiyat özelliğinin değerini döndürür
    public double getPrice() {
        return price.get();
    }

    // Fiyat özelliğini ayarlar
    public void setPrice(double price) {
        this.price.set(price);
    }

    // Fiyat özelliğinin DoubleProperty'sini döndürür
    public DoubleProperty priceProperty() {
        return price;
    }

    // Para birimi özelliğinin değerini döndürür
    public String getCurrency() {
        return currency.get();
    }

    // Para birimi özelliğini ayarlar
    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    // Para birimi özelliğinin StringProperty'sini döndürür
    public StringProperty currencyProperty() {
        return currency;
    }
}
