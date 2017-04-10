package joaopaulo.com.br.pingago.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joaopaulo.com.br.pingago.R;
import joaopaulo.com.br.pingago.adaptadores.AdaptadorDeListaLocais;
import joaopaulo.com.br.pingago.servicos.ServicoDeDados;


public class Localizacao_lista_fragmento extends Fragment {


    private RecyclerView recyclerView;
    public Localizacao_lista_fragmento() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Localizacao_lista_fragmento newInstance() {
        Localizacao_lista_fragmento fragment = new Localizacao_lista_fragmento();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_localizacao_lista_fragmento, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_localizacoes);
        recyclerView.setHasFixedSize(true);
        AdaptadorDeListaLocais adaptadorDeListaLocais = new AdaptadorDeListaLocais(ServicoDeDados.getInstance().trazerEstabelecimentosEmUmRadioDe10km(74610070));
        recyclerView.setAdapter(adaptadorDeListaLocais);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

}
