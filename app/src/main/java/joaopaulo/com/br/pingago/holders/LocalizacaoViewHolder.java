package joaopaulo.com.br.pingago.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import joaopaulo.com.br.pingago.R;
import joaopaulo.com.br.pingago.modelo.PingaGo;

/**
 * Created by minerthal on 10/04/17.
 */

public class LocalizacaoViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titulo;
    private TextView endereco;

    public LocalizacaoViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.localizacaoImagem);
        titulo = (TextView) itemView.findViewById(R.id.tituloLocal);
        endereco =(TextView) itemView.findViewById(R.id.enderecoDescricao);
    }

    public void updateViewUi(PingaGo pingaGo){
        String url = pingaGo.getLocalizacaoImagemURL();
        int recursoImagem =  imageView.getResources().getIdentifier(url,null,imageView.getContext().getPackageName());
        imageView.setImageResource(recursoImagem);
        titulo.setText(pingaGo.getLocalizacaoTitulo());
        endereco.setText(pingaGo.getLocalizacaoEndereco());
    }
}
