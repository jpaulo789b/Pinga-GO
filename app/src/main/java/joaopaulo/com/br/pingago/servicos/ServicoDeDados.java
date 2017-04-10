package joaopaulo.com.br.pingago.servicos;

import java.util.ArrayList;

import joaopaulo.com.br.pingago.modelo.PingaGo;

/**
 * Created by minerthal on 10/04/17.
 */

public class ServicoDeDados {
    private static final ServicoDeDados instance = new ServicoDeDados();

    public static ServicoDeDados getInstance() {
        return instance;
    }

    private ServicoDeDados() {

    }

    public ArrayList<PingaGo> trazerEstabelecimentosEmUmRadioDe10km(int cep){
        ArrayList<PingaGo> lista = new ArrayList<>();
        lista.add(new PingaGo(-49.2397134f,-16.6714993f,"Tio Zé","Tio Zé próximo a faculdade fatesg","slo"));
        lista.add(new PingaGo(-49.23957730000001f,-16.6720232f,"Chile's Bar","Av. Seila tô iventnado","slo"));
        lista.add(new PingaGo(-49.23834550000004f,-16.6701669f,"Pier 233","Tio Zé próximo a faculdade fatesg","slo"));
        lista.add(new PingaGo(-49.23983880000003f,-16.671487f,"Pingo De Ouro","Tio Zé próximo a faculdade fatesg","slo"));
        lista.add(new PingaGo(-49.241416500000014f,-16.6719702f,"Mestre da cerveja","Tio Zé próximo a faculdade fatesg","slo"));
        return lista;
    }
}
