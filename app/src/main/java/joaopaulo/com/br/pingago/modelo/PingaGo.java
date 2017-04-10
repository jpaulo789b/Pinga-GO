package joaopaulo.com.br.pingago.modelo;

/**
 * Created by minerthal on 10/04/17.
 */

public class PingaGo {
    private float longitude;
    private float latitude;
    private String localizacaoTitulo;
    private String localizacaoEndereco;
    private String localizacaoImagemURL;

    public PingaGo(float longitude, float latitude, String localizacaoTitulo, String localizacaoEndereco, String localizacaoImagemURL) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.localizacaoTitulo = localizacaoTitulo;
        this.localizacaoEndereco = localizacaoEndereco;
        this.localizacaoImagemURL = localizacaoImagemURL;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocalizacaoTitulo() {
        return localizacaoTitulo;
    }

    public String getLocalizacaoEndereco() {
        return localizacaoEndereco;
    }

    public String getLocalizacaoImagemURL() {
        return localizacaoImagemURL;
    }
}
