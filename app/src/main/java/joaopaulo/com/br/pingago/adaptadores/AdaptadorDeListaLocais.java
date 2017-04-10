package joaopaulo.com.br.pingago.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import joaopaulo.com.br.pingago.R;
import joaopaulo.com.br.pingago.holders.LocalizacaoViewHolder;
import joaopaulo.com.br.pingago.modelo.PingaGo;

/**
 * Created by minerthal on 10/04/17.
 */

public class AdaptadorDeListaLocais extends RecyclerView.Adapter<LocalizacaoViewHolder>{

    private ArrayList<PingaGo> locais;

    public AdaptadorDeListaLocais(ArrayList<PingaGo> locais) {
        this.locais = locais;
    }

    @Override
    public void onBindViewHolder(LocalizacaoViewHolder holder, int position) {
        final PingaGo location = locais.get(position);
        holder.updateViewUi(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locais.size();
    }

    @Override
    public LocalizacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_localizacao, parent, false);
        return new LocalizacaoViewHolder(card);
    }
}
